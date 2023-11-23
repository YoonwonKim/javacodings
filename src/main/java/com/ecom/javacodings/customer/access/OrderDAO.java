package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDAO {
	int setOrder(OrderDTO order);

	OrderDTO getOrder(OrderDTO order);
}
