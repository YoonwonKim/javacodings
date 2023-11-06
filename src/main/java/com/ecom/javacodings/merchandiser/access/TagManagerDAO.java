package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.TagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagManagerDAO {
	List<String> listTags();
	List<TagDTO> listTagsById(String item_id);

	int deleteTagsByItemId(String item_id);
	int insertTags(Map<String, Object> params);
}
