package com.ecom.javacodings.customer.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.customer.transfer.ItemDTO;


@Mapper
public interface ItemDAO {
	String a();
	List<ItemDTO> listNew(int number);
	List<ItemDTO> listBest(int number);
	List<ItemDTO> listItemsByTagId(String tagId);
	
	List<ItemDTO> listItem(int item);
	int updateList(ItemDTO item);
	int deleteItem(ItemDTO item);
	
	//RQ - 011 - 05 재고 데이터 변경
	int updateStockItem(ItemDTO item);
	//RQ - 012 - 02 상품관리페이지 제품 정보
	int listItemByItemId(ItemDTO item);
	//RQ - 011 - 06 가격 데이터 변경
	int updatePrice(ItemDTO item);
	
	int insertOrder(ItemDTO item);
    int updateOrderState(ItemDTO item);
    //RQ - 013 - 02 주문 리스트
	int orderList(ItemDTO item);
	
}
