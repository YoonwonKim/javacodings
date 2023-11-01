package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.transfer.BannerDTO;
import com.ecom.javacodings.customer.transfer.ItemDTO;
import com.ecom.javacodings.customer.transfer.TagDTO;

@Service
public interface CustomerService {
    // Region 회원 정보 관리 메소드
    MemberDTO login(MemberDTO member);
    // End Region 회원 정보 관리 메소드
    
    List<BannerDTO> getbanner(int number);
    
    List<BannerDTO> listEvent(int number);

	List<ItemDTO> listNew(ItemDTO item);
	
	List<ItemDTO> listBest(ItemDTO item);

	Map<String, Object> listTagById(String tagId);
	
	

}
