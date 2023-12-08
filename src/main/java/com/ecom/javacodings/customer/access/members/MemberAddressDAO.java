package com.ecom.javacodings.customer.access.members;

import com.ecom.javacodings.common.transfer.MemberAddressDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberAddressDAO {
	MemberAddressDTO findPrimaryByMemberId(@Param("member_id") String memberId);
	List<MemberAddressDTO> findAllByMemberId(@Param("member_id") String memberId);

	int add(MemberAddressDTO address);

	int editByPriorityAndMemberId(
			@Param("address") MemberDTO addressData,
			@Param("priority") int priority, @Param("member_id") String memberId);
	int setPriorityByPriorityAndMemberId(
			@Param("priority") int newPriority,
			@Param("criteria") int oldPriority,
			@Param("member_id") String memberId);

	int deleteByPriorityAndMemberID(
			@Param("priority") int priority,
			@Param("member_id") String memberId);
}
