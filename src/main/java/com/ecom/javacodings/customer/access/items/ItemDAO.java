package com.ecom.javacodings.customer.access.items;

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

	List<ItemDTO> findAllItemsByCategory(@Param("category") String category, @Param("page") PageDTO pageData);
	List<ItemDTO> findFewItemsOrderByOrderCount(@Param("limit") int limit);
	List<ItemDTO> findFewItemsOrderByRegDate(@Param("limit") int limit);
	List<ItemDTO> findFewItemsByTag(@Param("tag") String tag, @Param("limit") int limit);

	// End Region Basic CRUD
}
