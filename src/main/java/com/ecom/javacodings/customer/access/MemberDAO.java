package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDAO {
	// Region READ
	MemberDTO login(MemberDTO member);
	MemberDTO getMemberById(MemberDTO member);
	String searchId(MemberDTO member);
	// End Region READ

	int memberJoin(MemberDTO mdto);
	int idCheck(String member_id);
	int updateMembers(MemberDTO member);
	int updateMemberInfos(MemberDTO member);
	int updateAddress(MemberDTO member);
	int deleteMemberInfos(MemberDTO member);
	int deleteMembers(MemberDTO member);
	int deleteAddress(MemberDTO member);
	int temporaryPassword(MemberDTO member);

    MemberDTO getCurrentAddress(MemberDTO member);

    List<OrderDTO> countMemberOrders(MemberDTO member);
}
