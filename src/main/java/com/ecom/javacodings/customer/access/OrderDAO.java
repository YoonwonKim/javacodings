package com.ecom.javacodings.customer.access;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;

@Mapper
public interface OrderDAO {
	List<OrderDTO> orderItems(PageDTO page);
}
