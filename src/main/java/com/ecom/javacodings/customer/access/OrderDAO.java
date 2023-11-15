package com.ecom.javacodings.customer.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;

@Mapper
public interface OrderDAO {
	//장바구니
	List<OrderDTO> cartLists(PageDTO page);
	int updateCart(OrderDTO order);
	int deleteCart(OrderDTO order);
}
