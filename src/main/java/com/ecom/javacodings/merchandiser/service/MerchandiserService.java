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
    // 기본 CRUD 메소드 =================================
    @Override public int createItem(ItemDTO item) { return itemDAO.createItem(item); }
    @Override public ItemDTO readItemById(String id) { return itemDAO.getItemById(id); }
    @Override public int updateItem(ItemDTO item) { return itemDAO.updateItem(item); }
    @Override public int deleteItem(ItemDTO item) { return itemDAO.deleteItem(item); }

    @Override
    public List<ItemDTO> listItem(PageDTO page){
        List<ItemDTO> result = itemDAO.listItem(page);
        return result;
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
    public int countItems() { return itemDAO.countItems(); }
    // End Region 상품 메타 정보
    // Region Order
    // READ ===============================
    @Override
    public List<OrderDTO> listOrder(PageDTO page) {
        return orderDAO.listOrder(page);
    }
    @Override
    public int countOrders() { return orderDAO.countOrders(); }
    @Override
    public List<OrderDTO> countOrderState() {
        List<OrderDTO> result = orderDAO.countState();
        String[] states = {"장바구니", "결제 완료", "주문 확인", "배송 시작", "배송 중", "배송 완료", "환불", "반품", "처리 완료"};

        for (OrderDTO order : result) {
            order.setOrder_id(states[order.getState()]);
        }
        return result;
    }

    @Override
    public int updateImageById(ItemDTO item) {
        return itemDAO.updateImageById(item);
    }

    // EDIT ===============================
    @Override
    public int updateOrderStates(OrderDTO orders) {
        return orderDAO.updateOrderStates(orders); // 주문 상태 변경
    }
    // End Region Order
}
