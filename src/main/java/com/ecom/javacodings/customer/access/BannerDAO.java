package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.transfer.BannerDTO;

@Mapper
public interface BannerDAO {

	MemberDTO login(MemberDTO member);

	List<BannerDTO> listEvent(int number);
	
	List<BannerDTO> getbanner(int number);


	
	



	
 
}
