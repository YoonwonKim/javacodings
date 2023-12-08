package com.ecom.javacodings.external.purchase;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.MemberPaymentDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import lombok.Data;

@Data
public class RequestDTO {
    private String timestamp;
    private String signature;
    private String userAgent = "WP";
    private String quota = "0"; // 할부 기간
    private String returnUrl = "/order/return";

    // Order Info
    private String orderNumber;
    private String itemName;
    private String amount; // 총 가격

    // Payment Info
    private String cardNo; // 카드 번호
    private String cardPw; // 카드 비밀번호 앞두자리
    private String expireYear;
    private String expireMonth;

    // Member Info
    private String userName;
    private String userId;
    private String birthday;
    private String mobileNumber;
    private String userEmail;

    // Notification
    private String kakaoSend = "Y";


    public void setMember(MemberDTO member) {
        userId = member.getMember_id();
        userName = member.getName();
        birthday = member.getBirth();
        mobileNumber = "010" + member.getPhone();
        userEmail = member.getEmail();
    }

    public void setPayment(MemberPaymentDTO payment) {
        cardNo = payment.getCard_no();
        cardPw = payment.getCard_pw();
        expireYear  = payment.getExpire_year();
        expireMonth = payment.getExpire_month();
    }

    public void setOrder(OrderDTO order, int price) {
        orderNumber = order.getOrder_id();
        itemName = order.getItem_id();
        amount = String.valueOf(order.getQuantity() * price);
    }
}
