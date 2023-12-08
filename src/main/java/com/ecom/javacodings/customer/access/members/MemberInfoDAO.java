package com.ecom.javacodings.customer.access.members;

import com.ecom.javacodings.common.transfer.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberInfoDAO {

	MemberDTO findByMemberId(@Param("member_id") String memberId);

	int add(@Param("parameter") MemberDTO memberData);

	int editMemberInfoByMemberId(
			@Param("parameter") MemberDTO memberData,
			@Param("member_id") String memberId);
}
