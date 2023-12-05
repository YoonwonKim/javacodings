package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.TagDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagManagerDAO {
	// Region Read
	List<String> listTags();	//필요없어보임
	List<TagDTO> findTagsById(@Param("item_id") String item_id);
	// End Region Read
	// Region Create or Update
	int editTagsByItemId(Map<String, Object> params);	//몰루
	// End Region Create or Update
	// Region Delete
	int deleteTagsByItemId(@Param("item_id") String item_id);
	// End Region Delete
}
