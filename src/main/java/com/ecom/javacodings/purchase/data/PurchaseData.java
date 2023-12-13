package com.ecom.javacodings.purchase.data;

import lombok.Data;

@Data
public class PurchaseData {

    private String res_cd;
    private String res_msg;

    private String enc_data;  // 암호화된 인증 데이터
    private String enc_info;  // 암호화된 인증 데이터
    private String tran_cd;   // 인증코드

    private String userId;    // 일반 결제용, 유저 아이디
    private String buyr_mail; // 일반 결제용, 인증 데이터

}
