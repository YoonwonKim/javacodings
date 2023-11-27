package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.CartDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartDAO {
	List<CartDTO> listCart(Map<String, Object> params);
	int countCart(String member_id);

    int deleteCart(CartDTO item);
}