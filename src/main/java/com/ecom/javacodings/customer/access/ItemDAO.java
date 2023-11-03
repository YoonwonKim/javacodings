package com.ecom.javacodings.customer.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.customer.transfer.ItemDTO;


@Mapper
public interface ItemDAO {
	List<ItemDTO> listNew(int number);
	List<ItemDTO> listBest(int number);
	List<ItemDTO> listItemsByTagId(String tagId);
	
	List<ItemDTO> listItem(int item);
	int updateList(ItemDTO item);
	int deleteItem(ItemDTO item);
	int updatePrice(ItemDTO item);
	
	
}
