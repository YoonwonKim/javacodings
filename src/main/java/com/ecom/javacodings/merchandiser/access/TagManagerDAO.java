package com.ecom.javacodings.merchandiser.access;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagManagerDAO {
	List<String> listTags();
}
