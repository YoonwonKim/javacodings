package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.BannerDTO;

@Mapper
public interface BannerDAO {
	List<BannerDTO> getBannerByCategory(Map<String, Object> params);
}
