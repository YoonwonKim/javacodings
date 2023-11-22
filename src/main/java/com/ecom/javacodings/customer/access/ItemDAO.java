package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.javacodings.common.transfer.table.ItemDTO;

@Mapper
public interface ItemDAO {
	List<ItemDTO> listNew(int number);
	List<ItemDTO> listBest(int number);
	List<ItemDTO> listItemsByTagId(String tagId);
	
	//카테고리
	List<ItemDTO> getcategorylist(Map<String, Object> catepage);
}
