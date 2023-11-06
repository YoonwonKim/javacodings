package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.TagDTO;

import java.util.List;

public interface ManagerService {
    // Region 상품 관리
    List<ItemDTO> listItem(PageDTO page);

    int updateItem(ItemDTO item);
    int updateTags(String item_id, List<String> tags);




    int deleteItem(ItemDTO item);

    ItemDTO getItemById(String id);
    List<String> listCategory();



    List<String> listTags();

    List<TagDTO> listTagsById(String itemId);

	OrderDTO orderUpdate(OrderDTO order); //주문 상태 변경
}
