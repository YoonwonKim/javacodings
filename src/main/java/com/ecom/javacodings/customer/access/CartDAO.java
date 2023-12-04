package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartDAO {
	// Region Metadata

	int countCartByMemberID(String memberID);

	// End Region Metadata
	// Region Basic CRUD

	List<CartDTO> findAllCartByMemberID(Map<String, Object> params);
	Integer getQuantityByItemIDAndMemberID(CartDTO cart);

	int addCart(CartDTO cart);

	int updateCart(CartDTO cart);

	int deleteCartByMemberIDAndItemID(CartDTO cart);

	// End Region Basic CRUD
}