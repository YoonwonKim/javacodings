package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.NoticeDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.merchandiser.access.ItemManagerDAO;
import com.ecom.javacodings.merchandiser.access.NoticeManagerDAO;
import com.ecom.javacodings.merchandiser.access.OrderManagerDAO;
import com.ecom.javacodings.merchandiser.access.TagManagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("mdService")
public class MerchandiserService implements ManagerService {
    // Region Data access objects
    @Autowired ItemManagerDAO itemDAO;
    @Autowired TagManagerDAO  tagDAO;
    @Autowired OrderManagerDAO  orderDAO;
    // End Region Data access objects
    // Region 상품 관리

    @Override
    public List<ItemDTO> listItem(PageDTO page){
        List<ItemDTO> result = itemDAO.listItem(page);
        return result;
    }
    @Override
    public int updateItem(ItemDTO item) {
        return itemDAO.updateItem(item);
    }
    @Override
    public int updateTags(String item_id, List<String> tags) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("item_id", item_id);
        params.put("tags", tags);

        int result = 0;
        result += tagDAO.deleteTagsByItemId(item_id);
        if (!tags.isEmpty()) result *= tagDAO.insertTags(params);
        return result;
    }

    @Override
    public int deleteItem(ItemDTO item) {
        return itemDAO.deleteItem(item);
    }
    @Override
    public ItemDTO getItemById(String id) { return itemDAO.getItemById(id); }
    // Region 상품 메타 정보
    public List<String> listCategory() {
        List<String> result = itemDAO.listCategory();
        return result;
    }

    public List<String> listTags() {
        return tagDAO.listTags();
    }

    @Override
    public List<TagDTO> listTagsById(String itemId) {
        return tagDAO.listTagsById(itemId);
    }
    // End Region 상품 메타 정보
    // End Region 상품 관리
    
    @Override
    public OrderDTO orderUpdate(OrderDTO order) {
        return orderDAO.orderUpdate(order); // 주문 상태 변경
    }
    
    @Override
    public List<OrderDTO> orderList(PageDTO page) {
    	return orderDAO.orderList(page);
    }
    
  //RQ - 013 - 05 주문 상태 요약
    @Override
    public int orderStateCnt(OrderDTO order) {
    	return orderDAO.orderStateCnt(order);
    }
    
    //공지사항
    @Autowired
    NoticeManagerDAO noticeDAO;
    @Override
    public List<NoticeDTO>NoticeList(PageDTO page) {
    	return noticeDAO.NoticeList(page);
    }
    @Override
    public int NoticeGenerate(NoticeDTO notice) {
    	return noticeDAO.NoticeGenerate(notice);
    }
    @Override
    public int deleteNotice(NoticeDTO notice) {
    	return noticeDAO.deleteNotice(notice);
    }
    @Override
    public int updateNotice(NoticeDTO notice) {
    	return noticeDAO.updateNotice(notice);
    }
    @Override
    public NoticeDTO noticepage(NoticeDTO notice) {
    	return noticeDAO.noticepage(notice);
    }
    @Override
    public NoticeDTO updateformnotice(NoticeDTO notice) {
    	return noticeDAO.noticepage(notice);
    }
}
