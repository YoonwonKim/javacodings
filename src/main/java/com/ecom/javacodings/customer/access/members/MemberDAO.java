package com.ecom.javacodings.customer.access.members;

import com.ecom.javacodings.common.transfer.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDAO {
	// Region Metadata

	Boolean isExistMemberId(@Param("member_id") String memberId);

	// End Region Metadata
	// Region Basic CRUD

	MemberDTO findByIdAndPassword(@Param("member_id") String memberId, @Param("password") String password);
	String getMemberIdByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

	int add(@Param("member") MemberDTO memberData);
	int setPasswordByMemberId(@Param("password") String password, @Param("member_id") String memberId);

	int archiveByMemberId(@Param("member_id") String memberId);

    MemberDTO findByIdAndName(@Param("member_id") String memberId, @Param("name") String memberName);

    // End Region Basic CRUD
}
