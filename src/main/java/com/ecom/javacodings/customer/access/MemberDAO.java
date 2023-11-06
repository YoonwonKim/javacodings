package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    MemberDTO login(MemberDTO member);
}
