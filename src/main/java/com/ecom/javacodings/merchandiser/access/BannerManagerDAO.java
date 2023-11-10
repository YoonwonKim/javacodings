package com.ecom.javacodings.merchandiser.access;

import com.ecom.javacodings.common.transfer.table.BannerDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerManagerDAO {
	
	BannerDTO bannermain(BannerDTO banner);
	BannerDTO bannerevent(BannerDTO banner);
}
