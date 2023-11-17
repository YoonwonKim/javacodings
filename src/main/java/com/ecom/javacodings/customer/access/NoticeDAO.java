package com.ecom.javacodings.customer.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.NoticeDTO;

@Mapper
public interface NoticeDAO {
	List<NoticeDTO> NoticeList(PageDTO page);
	
	NoticeDTO noticepage(NoticeDTO notice);
	
}
