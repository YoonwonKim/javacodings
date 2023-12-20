package com.ecom.javacodings.customer.access.events;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.EventBannerDTO;
import com.ecom.javacodings.common.transfer.EventDTO;
import com.ecom.javacodings.common.transfer.EventItemDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;

@Mapper
public interface EventDAO {
	
	int count();
	List<EventBannerDTO> findAll(@Param("page") PageDTO page);
	List<String> getAllItemLabelByEventId(@Param("event_id") String eventId);
	
	List<EventBannerDTO> mainBanner(@Param("event_id") String eventId);
	List<ItemDTO> eventItem(@Param("event_id") String eventId);
	String getEventById(String event_id);
	
	
	

}
