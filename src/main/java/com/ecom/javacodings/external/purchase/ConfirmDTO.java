package com.ecom.javacodings.external.purchase;

import lombok.Data;

@Data
public class ConfirmDTO {
    private String ordr_idxx; // 거래번호
    private String tran_cd;   // 인증코드

    // 암호화 정보
    private String res_cd;
    private String enc_info;
    private String enc_data;

    // 추가 데이터
    private String userId;    // 일반 결제용, 유저 아이디
    private String buyr_mail; // 일반 결제용, 인증 데이터
    private String card_pay_method; // 카카오페이 결제용, 결제 타입
}
