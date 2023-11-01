package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.customer.transfer.ItemDTO;

@Mapper
public interface ItemDAO {

	List<ItemDTO> listNew(ItemDTO item);
	
	List<ItemDTO> listBest(ItemDTO item);

	List<ItemDTO> listTagById();

	

}
