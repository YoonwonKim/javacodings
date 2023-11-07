package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.table.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagManagerDAO {
	// Region Read
	List<String> listTags();
	List<TagDTO> listTagsById(String item_id);
	// End Region Read
	// Region Create or Update
	int insertTags(Map<String, Object> params);
	// End Region Create or Update
	// Region Delete
	int deleteTagsByItemId(String item_id);
	// End Region Delete
}
