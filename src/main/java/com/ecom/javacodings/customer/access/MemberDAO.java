package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    MemberDTO login(MemberDTO member);    
    String searchId(MemberDTO member);
	int memberJoin(MemberDTO mdto);
	int idCheck(String member_id);
	int temporaryPassword(MemberDTO member);
}
