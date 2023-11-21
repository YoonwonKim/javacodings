package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    MemberDTO login(MemberDTO member);    
    String searchId(MemberDTO member);
	int memberJoin(MemberDTO mdto);
	int idCheck(String member_id);
	int updateMembers(MemberDTO member);
	int updateMemberInfos(MemberDTO member);
	int updateAddress(MemberDTO member);
	int deleteMemberInfos(MemberDTO member);
	int deleteMembers(MemberDTO member);
	int deleteAddress(MemberDTO member);
	int temporaryPassword(MemberDTO member);
}
