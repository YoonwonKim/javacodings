package com.ecom.javacodings.customer.access;

import java.util.List;

import com.ecom.javacodings.common.transfer.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.OrderDTO;

@Mapper
public interface OrderDAO {


	// Region Metadata

	List<OrderDTO> countOrdersByMemberID(String memberID);

	Boolean isDuplicatedID(String generatedId);

	// End Region Metadata
	// Region Basic CRUD

	int addOrder(CartDTO item);
	int updateOrderStateByOrderID(OrderDTO order);

	// End Region Basic CRUD
}