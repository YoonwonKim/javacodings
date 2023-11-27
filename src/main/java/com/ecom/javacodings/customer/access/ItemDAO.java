package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.ItemDTO;

@Mapper
public interface ItemDAO {
	List<ItemDTO> listNew(int number);
	List<ItemDTO> listBest(int number);
	List<ItemDTO> listItemsByTagId(String tagId);
	List<ItemDTO> getListItem();
	int getItemCnt();
	ItemDTO listItemDt(ItemDTO itemDTO);
	
	//카테고리
	List<ItemDTO> listProductsInCategory(Map<String, Object> properties);

	int countProductsInCategory(String category);
}
