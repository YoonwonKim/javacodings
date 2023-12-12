package com.ecom.javacodings.purchase.data;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.purchase.method.Signature;
import lombok.Getter;
import lombok.ToString;

import java.security.NoSuchAlgorithmException;

@Getter
public class RequestData {

    private String signature;   // 서명값
    public void setSignature(String merchantId, String apiCertKey) {
        String[] dataList = {merchantId, orderNumber, String.valueOf(amount), apiCertKey, timestamp};
        Signature signatureMethod = new Signature();
        try { signature = signatureMethod.encrypt(dataList); }
        catch (NoSuchAlgorithmException e) { signature = null; }
    }

    private String timestamp;   // 타임스탬프, YYYYMMDDHHMI24SS 형식
    private String bypassValue = ""; // 가맹점에서 사용가능한 추가 필드, 최대 255자, 선택 요소

    private String orderNumber; // 주문 번호
    private String amount;         // 총 결제 금액
    private String itemName;    // 상품 이름
    private String userName;    // 구매자 이름
    private String userEmail;   // 구매자 이메일, 선택 요소

    private final String userAgent = "WP"; // 사용자 환경
    private final String returnUrl = "/order";
    private RequestData requestData;


    public RequestData(OrderDTO orderData, MemberDTO memberInfoData, String itemLabel) {

        // 사용자 정보 설정
        userName = memberInfoData.getName();
        userEmail = memberInfoData.getEmail();

        // 주문 정보 설정
        orderNumber = orderData.getOrder_id();
        itemName = itemLabel;
        amount = String.valueOf(orderData.getAmount());
        timestamp = orderData.getReg_date();

    }

}
