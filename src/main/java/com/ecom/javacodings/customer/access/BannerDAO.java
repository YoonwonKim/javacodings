package com.ecom.javacodings.customer.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.table.BannerDTO;

@Mapper
public interface BannerDAO {
	List<BannerDTO> listEvent();
	List<BannerDTO> listMain(int number);
}
