package com.ecom.javacodings.purchase.service;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.access.items.ItemDAO;
import com.ecom.javacodings.customer.access.members.MemberInfoDAO;
import com.ecom.javacodings.customer.access.members.MemberPaymentDAO;
import com.ecom.javacodings.purchase.data.PurchaseData;
import com.ecom.javacodings.purchase.data.RequestData;
import com.ecom.javacodings.purchase.method.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("kcpService")
public class KcpService implements IPurchaseService {

    @Value("${purchase.payup.merchant-id}") String MERCHANT_ID;
    @Value("${purchase.payup.cert-key}") String CERT_KEY;
    @Value("${purchase.payup.test-url}") String PAYUP_URL;

    @Autowired ItemDAO itemDAO;
    @Autowired MemberInfoDAO memberInfoDAO;
    @Autowired MemberPaymentDAO paymentDAO;

    Transaction transaction = new Transaction();

    @Override
    public Map<String, String> request(OrderDTO orderData) {
        MemberDTO orderMemberInfoData = memberInfoDAO.findByMemberId(orderData.getMember_id());
        ItemDTO itemData = itemDAO.findByItemId(orderData.getItem_id());
        RequestData requestData = new RequestData(orderData, orderMemberInfoData, itemData.getLabel());
        requestData.setSignature(MERCHANT_ID, CERT_KEY);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestMap = mapper.convertValue(requestData, Map.class);
        String url = PAYUP_URL + MERCHANT_ID + "/order";
        Map<String, String> responseBody;

        try { responseBody = transaction.request(url, requestMap); }
        catch (Exception e) { return null; }
        return responseBody;
    }

    @Override
    public Map<String, String> purchase(String ordr_idxx, PurchaseData purchaseData) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestMap = mapper.convertValue(purchaseData, Map.class);
        String url = PAYUP_URL + ordr_idxx + "/pay";

        Map<String, String> responseBody;
        try { responseBody = transaction.request(url, requestMap); }
        catch (Exception e) { return null; }
        return responseBody;
    }

    @Override
    public Map<String, String> cancel() {
        return null;
    }

    @Override
    public Map<String, String> partialCancel() {
        return null;
    }

}
