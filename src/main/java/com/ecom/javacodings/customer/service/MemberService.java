package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.access.MemberDAO;
import com.ecom.javacodings.customer.transfer.BannerDTO;
import com.ecom.javacodings.customer.transfer.ItemDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService implements CustomerService {
    @Autowired
    MemberDAO memberDAO;

    @Override
    public MemberDTO login(MemberDTO member) {
        return memberDAO.login(member);
    }

	@Override
	public BannerDTO getbanner(BannerDTO banner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BannerDTO> EventList(BannerDTO banner) {
		// TODO Auto-generated method stub
		return null;
	}
}
