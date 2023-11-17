package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.table.NoticeDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeManagerDAO {
	List<NoticeDTO> NoticeList(PageDTO page);
	
	NoticeDTO noticepage(NoticeDTO notice);
	
	int NoticeGenerate(NoticeDTO notice);
	
	int deleteNotice(NoticeDTO notice);

	int updateNotice(NoticeDTO notice);

}
