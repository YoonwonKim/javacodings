package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.ItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemManagerDAO {
	// Region Read
	//? List
	List<ItemDTO> findAllItemsByItemId(@Param("item_id") String item_id);
	List<String> findFewItemsByCategory(@Param("category") String category);
	//? Get
	ItemDTO getItemsByItemId(@Param("item_id") String item_id);
	int countItemsByItemId(@Param("item_id") String item_id);
	// End Region Read
	// Region Create or Update
	int addItems(@Param("item_id") String item_id);
	int editItems(@Param("item_id") String item_id);
	int addItemImagesByItemId(@Param("path") String path, @Param("item_id") String item_id);	//Mapper,Service,Controller에 추가 생성 필요
	int editItemImagesByItemId(@Param("criteria") String oldPath, @Param("path") String path, @Param("item_id") String item_id);
	// End Region Create or Update
	// Region Delete
	int deleteItemByItemId(@Param("item_id") String item_id);
	int deleteItemImagesByItemId(@Param("path") String path, @Param("item_id") String item_id);
	int deleteItemTegsByItemId(@Param("tag") String tag, @Param("item_id") String item_id);
    // End Region Delete
}
