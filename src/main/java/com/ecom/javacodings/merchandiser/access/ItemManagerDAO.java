package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemManagerDAO {
	List<ItemDTO> listItem(PageDTO page);
	int updateItem(ItemDTO item);
	int deleteItem(ItemDTO item);
	ItemDTO getItemById(String id);

	List<String> listCategory();
}
