package com.ecom.javacodings.customer.access;

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
	int getQuantityOfCartByItemIdAndMemberId(@Param("member_id") String memberId, @Param("item_id") String itemId);

	int addCart(@Param("cart") CartDTO cart);

	int setQuantityOfCartByItemIdAndMemberId(@Param("quantity") int quantity, @Param("member_id") String memberId, @Param("item_id") String itemId);

	int deleteCartByMemberIdAndItemId(@Param("member_id") String memberId, @Param("item_id") String itemId);

	// End Region Basic CRUD
}