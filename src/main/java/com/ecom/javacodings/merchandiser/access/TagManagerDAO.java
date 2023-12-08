package com.ecom.javacodings.merchandiser.access;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagManagerDAO {
	// Region Read

	List<String> findAll();

	// End Region Read
	// Region Create or Update
	int editTagsByItemId(Map<String, Object> params);	//몰루
	// End Region Create or Update
	// Region Delete
	int deleteTagsByItemId(String item_id);

	String[] findAllByItemId(@Param("item_id") String itemId);
	// End Region Delete
}
