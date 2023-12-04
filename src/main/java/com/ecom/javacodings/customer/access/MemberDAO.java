package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDAO {
	// Region Metadata

	int isExistMemberID(String member_id);

	// End Region Metadata
	// Region Basic CRUD

	MemberDTO findMemberByIDAndPassword(MemberDTO member);
	String getMemberIDByNameAndPhone(MemberDTO member);

	int addMember(MemberDTO member);

	int setPassword(String temporaryPassword);

	int archiveMemberByMemberID(String memberID);

	// End Region Basic CRUD
	// Region Sub-Tables

	MemberDTO getAddressByMemberID(String memberID);

	int updateAddress(MemberDTO member);
	int updateAddressPriority(MemberDTO member);
	int updateMemberInfo(MemberDTO member);

	int deleteAddressByPriorityAndMemberID(Map<String, Object> params);

	// End Region Sub-Tables
}
