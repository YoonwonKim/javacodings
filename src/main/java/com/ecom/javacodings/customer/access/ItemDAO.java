package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.ItemDTO;

@Mapper
public interface ItemDAO {
	// Region Metadata

	Integer countItems();
	Integer countItemsByTagID(String tagID);
	Integer countItemsByCategory(String category);

	// End Region Metadata
	// Region Basic CRUD

	ItemDTO findItemByItemID(String itemID);

	List<ItemDTO> findAllItemsByTagID(Map<String, Object> params);
	List<ItemDTO> findAllItemsByCategory(Map<String, Object> params);
	List<ItemDTO> findAllItemsOrderByOrderCount(Map<String, Object> params);
	List<ItemDTO> findAllItemsOrderByRegDate(Map<String, Object> params);

	// End Region Basic CRUD
}
