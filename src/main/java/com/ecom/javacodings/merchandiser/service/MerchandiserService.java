package com.ecom.javacodings.merchandiser.service;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
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
    public int updateOrderStates(List<OrderDTO> orders) {
        return ordermanagerDAO.updateOrderStates(orders); // 주문 상태 변경
    }
    
    @Override
    public List<OrderDTO> listOrder(PageDTO page) {
    	return ordermanagerDAO.listOrder(page);
    }
    
  //RQ - 013 - 05 주문 상태 요약
    @Override
    public List<OrderDTO> countOrderState() {
        List<OrderDTO> result = ordermanagerDAO.countState();
        for (OrderDTO order : result) {
            switch (order.getState()) {
                case 1: order.setOrder_id("결제 완료"); break;
                case 2: order.setOrder_id("주문 확인"); break;
                case 3: order.setOrder_id("배송 시작"); break;
                case 4: order.setOrder_id("배송 중"); break;
                case 5: order.setOrder_id("배송 완료"); break;
                case 6: order.setOrder_id("환불"); break;
                case 7: order.setOrder_id("반품"); break;
                case 8: order.setOrder_id("처리 완료"); break;
            }
        }
        return result;
    }
}
