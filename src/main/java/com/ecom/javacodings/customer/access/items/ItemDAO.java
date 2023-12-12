package com.ecom.javacodings.customer.access.items;

import java.util.List;

import com.ecom.javacodings.common.page.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.ItemDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemDAO {
	// Region Metadata

	int count();
	int countByTag(@Param("tag") String tag);
	int countByCategory(@Param("category") String category);

	// End Region Metadata
	// Region Basic CRUD

	ItemDTO findByItemId(@Param("item_id") String itemId);

	List<ItemDTO> findAllByCategory(@Param("category") String category, @Param("page") PageDTO pageData);
	List<ItemDTO> findAllByOrderCount(@Param("limit") int limit);
	List<ItemDTO> findAllByRegDate(@Param("limit") int limit);
	List<ItemDTO> findAllByTag(@Param("tag") String tag, @Param("limit") int limit);

    int decreaseStockByItemId(@Param("item_id") String itemId);

    // End Region Basic CRUD
}
