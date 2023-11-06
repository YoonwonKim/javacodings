package com.ecom.javacodings.customer.access;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.OrderDTO;

@Mapper
public interface OrderDAO {
	
	OrderDTO orderUpdate(OrderDTO order);

}
