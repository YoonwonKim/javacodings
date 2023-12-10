package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.ItemImageDTO;
import com.ecom.javacodings.common.transfer.SummaryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemManagerDAO {
	// Region Read

	List<SummaryDTO> summaryItemsByCategory();
	List<SummaryDTO> summaryItemsByTag();
	int count();
	List<String> listCategory();


	List<ItemDTO> findAll(PageDTO page);


	//? Get
	ItemDTO getItemById(String item_id);
	// End Region Read
	// Region Create or Update
	int createItem(ItemDTO item);
	int updateItem(ItemDTO item);
	int updateImageById(@Param("item_id") String itemId, @Param("image_id") String imageId);
	// End Region Create or Update
	// Region Delete
	int deleteItem(ItemDTO item);
	int deleteItemImages(ItemDTO item);
	int deleteItemTegs(ItemDTO item);

	List<ItemImageDTO> findImagesByItemId(@Param("item_id") String itemId);


	// End Region Delete
}
