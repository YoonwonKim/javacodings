package com.ecom.javacodings.merchandiser.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.common.transfer.PageDTO;

@Mapper
public interface OrderManagerDAO {

	int updateOrderStates(List<OrderDTO> orders);

	List<OrderDTO> listOrder(PageDTO page);
	
	List<OrderDTO> countState();
}
