package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.NoticeDTO;
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

    OrderDTO orderUpdate(OrderDTO order);
    List<OrderDTO> orderList(PageDTO page);int orderStateCnt(OrderDTO order);
    // End Region Item
    
    //공지사항
	List<NoticeDTO> NoticeList(PageDTO page);
	int NoticeGenerate(NoticeDTO notice);
	int deleteNotice(NoticeDTO notice);
	int updateNotice(NoticeDTO notice);
	NoticeDTO noticepage(NoticeDTO notice);
	NoticeDTO updateformnotice(NoticeDTO notice);
}
