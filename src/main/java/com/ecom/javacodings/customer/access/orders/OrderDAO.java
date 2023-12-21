package com.ecom.javacodings.customer.access.orders;

import java.util.List;

import com.ecom.javacodings.common.transfer.CartDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {
	// Region Metadata

	List<OrderDTO> countOrdersByMemberId(@Param("member_id") String memberId);
	
	List<OrderDTO> findAllByMemberOrderOrders(@Param("member_id") String memberId);
	
	List<CartDTO> findAllByMemberOrderItems(@Param("member_id") String memberId, @Param("order_id") String orderId);
	
	List<OrderDTO> findOrderItemsByOrderId(@Param("order_id") String orderId);
	
	List<ItemDTO> findItemsByOrderId(@Param("order_id") String orderId);
	
	Boolean isExistOrderId(@Param("order_id") String orderId);

	// End Region Metadata
	// Region Basic CRUD --------------------------------------------------------------------------------

	// * CREATE ------------

	int add(@Param("order") OrderDTO orderData);

	// * READ ------------

	OrderDTO findUnPayedOrderByOrderId(@Param("order_id") String orderId);
	List<CartDTO> findAllCartByOrderId(@Param("order_id") String orderId);

	String getItemIdByOrderId(@Param("order_id") String orderId);

	// * UPDATE ------------

	int increaseStateByOrderId(@Param("order_id") String orderId, @Param("reg_date") String regDate);
	int setTransactionIdByOrderId(@Param("transaction_id") String transactionId, @Param("order_id") String orderId);

	List<String> getAllItemIdByOrderId(String orderId);

	List<ItemDTO> findAllItemsByOrderId(String orderId);

    // End Region Basic CRUD
}