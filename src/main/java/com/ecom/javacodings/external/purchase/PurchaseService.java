package com.ecom.javacodings.external.purchase;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface PurchaseService {
    String requestPurchase(OrderDTO order, ItemDTO item, MemberDTO member)
            throws NoSuchAlgorithmException, IOException, InterruptedException;

    Map<String, String> requestOrder(OrderDTO order, ItemDTO item, MemberDTO member)
            throws NoSuchAlgorithmException, IOException, InterruptedException;

    Map<String, String> confirmOrder(MemberDTO member, Map<String, String> params)
            throws NoSuchAlgorithmException, IOException, InterruptedException;

    String requestCancel(OrderDTO order, ItemDTO item)
            throws NoSuchAlgorithmException, IOException, InterruptedException;

    Map<String, String> requestKakaoOrder(OrderDTO order, ItemDTO item, MemberDTO member)
            throws NoSuchAlgorithmException, IOException, InterruptedException;

    Map<String, String> requestKakaoConfirm(ConfirmDTO confirmTransaction)
            throws IOException, InterruptedException;

    Map<String, String> requestKakaoCancel(OrderDTO order, String reason)
            throws IOException, InterruptedException, NoSuchAlgorithmException;
}
