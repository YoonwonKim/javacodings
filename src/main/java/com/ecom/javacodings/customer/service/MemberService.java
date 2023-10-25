package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.access.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService implements CustomerService {
    @Autowired
    MemberDAO memberDAO;

    @Override
    public int login(MemberDTO member) {
        return memberDAO.login(member);
    }
}
