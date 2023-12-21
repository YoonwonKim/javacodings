package com.ecom.javacodings.merchandiser.access;

import java.util.List;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.SummaryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecom.javacodings.common.transfer.OrderDTO;

@Mapper
public interface OrderManagerDAO {

	int count();

	List<OrderDTO> findAll(@Param("page") PageDTO pageData);

	OrderDTO addOrderByOrderId(@Param("order_id") String order_id);
	
	int setStateOfOrderByOrederId(@Param("state") int orderState, @Param("order_id") String order_id);

	List<OrderDTO> findAllOrderByOrderStates(@Param("state") int orderState, @Param("order_id") String order_id);
	
	List<OrderDTO> countOrdersByStates(@Param("state") int orderState);

    int countOrdersByOrderId(@Param("order_id") String order_id);

	int insertOrder(OrderDTO order);	//보류

	List<OrderDTO> orderList(PageDTO page);

	OrderDTO orderUpdate(OrderDTO order);

	int countOrders();

	List<SummaryDTO> countState();

	int orderStateCnt(OrderDTO order);

	int updateOrderStates(OrderDTO orders);
}
