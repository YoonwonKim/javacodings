package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDAO {
	// Region Metadata

	int isExistMemberId(@Param("member_id") String memberId);

	// End Region Metadata
	// Region Basic CRUD

	MemberDTO findMemberByIdAndPassword(@Param("member_id") String memberId, @Param("password") String password);
	String getMemberIdByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

	int addMember(@Param("member") MemberDTO memberData);
	int setPasswordByMemberId(@Param("password") String password, @Param("member_id") String memberId);

	int archiveMemberByMemberId(@Param("member_id") String memberId);

	// End Region Basic CRUD
	// Region Sub-Tables

	MemberDTO getAddressByMemberId(@Param("member_id") String memberId);

	int editMemberInfo(@Param("member") MemberDTO memberData);
	int editAddress(@Param("address") MemberDTO addressData);
	int editPriorityOfAddressByPriority(@Param("priority") int newPriority, @Param("criteria") int oldPriority);

	int deleteAddressByPriorityAndMemberID((@Param("member_id") String memberId, @Param("priority") int priority);

	// End Region Sub-Tables
}
