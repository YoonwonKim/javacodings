package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.BannerDTO;
import com.ecom.javacodings.common.transfer.EventBannerDTO;

@Mapper
public interface BannerDAO {
	List<EventBannerDTO> getBannerByCategory(Map<String, Object> params);
}
