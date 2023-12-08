package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.table.EventDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ManagerService {
    // Region Item
    // Read table
    List<ItemDTO> listItem(PageDTO page);
    ItemDTO getItemById(String id);
    // Create and Update
    int updateItem(ItemDTO item);
    int updateTags(String item_id, List<String> tags);
    // Delete
    int deleteItem(ItemDTO item);

    //? Get Metadata
    List<String> listCategory();
    List<String> listTags();
    List<TagDTO> listTagsById(String itemId);

    // Region Orders
    int updateOrderStates(List<OrderDTO> orders);
    //RQ - 013 - 02 주문 리스트
    List<OrderDTO> listOrder(PageDTO page);

    //RQ - 013 - 05 주문 상태 요약
    List<OrderDTO> countOrderState();
    // End Region Orders
    
    //이벤트 리스트
    //List<EventDTO> listEvents(PageDTO page);
    List<EventDTO> listEvent(PageDTO pageDTO);
    
    //상품,이벤트 리스트
    List<ItemDTO> listEventItem(PageDTO page);
    
    
    //세일 기능
    int event1(EventDTO eventDTO);
    // 증정 기능
    int event2(EventDTO eventDTO);
    // 이벤트 추가
    int eventAdd(EventDTO eventDTO);
    
	void stateUpdate(EventDTO eventDTO);
}
