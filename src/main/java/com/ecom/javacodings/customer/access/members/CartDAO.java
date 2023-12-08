package com.ecom.javacodings.customer.access.members;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.CartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDAO {
	// Region Metadata

	int countCartByMemberId(@Param("member_id") String memberId);

	// End Region Metadata
	// Region Basic CRUD

	List<CartDTO> findAllCartByMemberId(@Param("member_id") String memberId, @Param("page") PageDTO pageData);
	Integer getQuantityByItemIdAndMemberId(@Param("member_id") String memberId, @Param("item_id") String itemId);

	int addCart(@Param("cart") CartDTO cart);

	int editCartByItemIdAndMemberId(CartDTO cart);

	int deleteByMemberIdAndItemId(@Param("member_id") String memberId, @Param("item_id") String itemId);

	// End Region Basic CRUD
}