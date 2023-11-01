package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.access.BannerDAO;
import com.ecom.javacodings.customer.transfer.BannerDTO;
import com.ecom.javacodings.customer.transfer.ItemDTO;

@Service("bannerService")
public class BannerService implements CustomerService {
	
	@Autowired
	BannerDAO bannerDAO;

	@Override
	public MemberDTO login(MemberDTO member) {
		
		return null;
	}

	@Override
	public List<BannerDTO> getbanner(int number) {
		return bannerDAO.getbanner(number);
	}

	@Override
	public List<BannerDTO> listEvent(int number) {
		
		return bannerDAO.listEvent(number);	
	}
}
