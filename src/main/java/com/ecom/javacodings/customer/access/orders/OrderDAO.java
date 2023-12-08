package com.ecom.javacodings.customer.access.orders;

import java.util.List;

import com.ecom.javacodings.common.transfer.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.OrderDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {
	// Region Metadata

	List<OrderDTO> countOrdersByMemberId(@Param("member_id") String memberId);

	Boolean isExistOrderId(@Param("order_id") String orderId);

	// End Region Metadata
	// Region Basic CRUD

	int addOrder(@Param("item") CartDTO itemData);
	int setStateOfOrderByOrderID(@Param("state") int orderState, @Param("order_id") String orderId);

	// End Region Basic CRUD
}