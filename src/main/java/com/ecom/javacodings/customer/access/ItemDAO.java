package com.ecom.javacodings.customer.access;

import java.util.List;

import com.ecom.javacodings.common.page.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.ItemDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemDAO {
	// Region Metadata

	int countItems();
	int countItemsByTag(@Param("tag") String tag);
	int countItemsByCategory(@Param("category") String category);

	// End Region Metadata
	// Region Basic CRUD

	ItemDTO findItemByItemId(@Param("item_id") String itemId);

	List<ItemDTO> findAllItemsByTag(@Param("tag") String tag, @Param("limit") int limit);
	List<ItemDTO> findAllItemsOrderByOrderCount(@Param("limit") int limit);
	List<ItemDTO> findAllItemsOrderByRegDate(@Param("limit") int limit);
	List<ItemDTO> findAllItemsByCategory(@Param("category") String category, @Param("page") PageDTO pageData);

	// End Region Basic CRUD
}
