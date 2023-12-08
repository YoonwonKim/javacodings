package com.ecom.javacodings.merchandiser.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.EventDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;

@Mapper
public interface EventManagerDAO {
	
	//List<EventDTO> listEvents(Map<String, Object> params);
	List<EventDTO> listEvent(PageDTO pageDTO);
	
	List<ItemDTO> listEventItem(PageDTO page);
	
	void stateUpdate(EventDTO eventDTO);
	
	int event1(EventDTO eventDTO);
	
	int event2(EventDTO eventDTO);

	int eventAdd(EventDTO eventDTO);

}
