package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.transfer.MemberDTO;

public interface CustomerService {
    // Region 회원 정보 관리 메소드
    MemberDTO login(MemberDTO member);
    // End Region 회원 정보 관리 메소드
}
