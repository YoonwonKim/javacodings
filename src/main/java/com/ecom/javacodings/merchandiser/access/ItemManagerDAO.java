package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemManagerDAO {
	// Region Read
	//? List
	List<ItemDTO> listItem(PageDTO page);
	List<String> listCategory();
	//? Get
	ItemDTO getItemById(String item_id);
	// End Region Read
	// Region Create or Update
	int updateItem(ItemDTO item);
	// End Region Create or Update
	// Region Delete
	int deleteItem(ItemDTO item);
	int updateItem(ItemDTO item);

	List<String> listCategory();
	ItemDTO getItemById(String item_id);
}
