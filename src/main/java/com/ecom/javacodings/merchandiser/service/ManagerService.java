package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;

import java.util.List;

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
}
