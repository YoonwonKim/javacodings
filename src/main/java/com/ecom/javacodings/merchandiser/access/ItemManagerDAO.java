package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.ItemImageDTO;
import com.ecom.javacodings.common.transfer.SummaryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemManagerDAO {

	Boolean isExistId(@Param("item_id") String itemId);

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
	int addAllTagsWithItemId(@Param("item_id") String item_id, @Param("tagList") List<String> tags);
	int updateItem(ItemDTO item);
	int updateImageById(@Param("item_id") String itemId, @Param("image") Map<String, String> imageData);

	// End Region Create or Update
	// Region Delete
	int deleteItem(ItemDTO item);
	int deleteItemImages(ItemDTO item);
	int deleteAllTagsByItemId(@Param("item_id") String itemId);

	List<ItemImageDTO> findImagesByItemId(@Param("item_id") String itemId);

	Integer deleteAllImagesByItemId(@Param("item_id") String itemId);
	Integer addAllImagesByItemId(@Param("item_id") String itemId, @Param("image_list") List<Object> itemImageList);


	// End Region Delete
}
