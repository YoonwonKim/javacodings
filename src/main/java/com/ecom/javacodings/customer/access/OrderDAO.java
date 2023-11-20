package com.ecom.javacodings.customer.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;

@Mapper
public interface OrderDAO {
	//장바구니 시작
	List<OrderDTO> cartLists(PageDTO page);
	List<OrderDTO> updateCart(List<OrderDTO> orderList);
	List<OrderDTO> deleteOrdersByCart(List<OrderDTO> orderList);
	List<OrderDTO> deleteOrderStateByCart(List<OrderDTO> orderList);
	//장바구니 끝
}
