package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.customer.transfer.BannerDTO;

@Mapper
public interface BannerDAO {
	List<BannerDTO> listEvent();
	List<BannerDTO> listMain(int number);
}
