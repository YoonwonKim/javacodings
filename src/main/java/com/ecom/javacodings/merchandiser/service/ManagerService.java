package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.TagDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManagerService {
    //int updateImage(ItemDTO item, MultipartFile file);

    // End Region Data access objects
    // Region Item
    // Read table
    List<ItemDTO> listItem(PageDTO page);
    ItemDTO readItemById(String id);
    // Create and Update
    int updateItem(ItemDTO item);
    int updateImageById(ItemDTO item, MultipartFile file);

    int createItem(ItemDTO item);

    int updateTags(String item_id, List<String> tags);
    // Delete
    int deleteItem(ItemDTO item);
    int deleteItemImages(ItemDTO item);
	int deleteItemTegs(ItemDTO item);

    //? Get Metadata
    List<String> listCategory();
    List<String> listTags();
    List<TagDTO> listTagsById(String itemId);
    int countItems();
    // Region Orders
    int updateOrderStates(OrderDTO orders);
    //RQ - 013 - 02 주문 리스트
    List<OrderDTO> listOrder(PageDTO page);
    int countOrders();

    //RQ - 013 - 05 주문 상태 요약
    List<OrderDTO> countOrderState();

    // End Region Orders

    OrderDTO orderUpdate(OrderDTO order);
    List<OrderDTO> orderList(PageDTO page);int orderStateCnt(OrderDTO order);
    // End Region Item
}
