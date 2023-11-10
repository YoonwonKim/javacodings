package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.table.BannerDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.merchandiser.access.BannerManagerDAO;
import com.ecom.javacodings.merchandiser.access.ItemManagerDAO;
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
    @Autowired BannerManagerDAO bannerDAO;
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
    
    //배너관리 메인
    public BannerDTO bannermain(BannerDTO banner) {
    	return bannerDAO.bannermain(banner);
    }
    //배너관리 이벤트
    public BannerDTO bannerevent(BannerDTO banner) {
    	return bannerDAO.bannerevent(banner);
    }
}
