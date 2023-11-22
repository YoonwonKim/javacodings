package com.ecom.javacodings.merchandiser.access;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.MemberDTO;

@Mapper
public interface MemberManagerDAO {
	List<MemberDTO> listMember(PageDTO page);
	
	int countMembers();
	
	int deleteMembers(MemberDTO member);
	
	int deleteMember_Infos(MemberDTO member);
	
	int deleteOrders(MemberDTO member);
}
