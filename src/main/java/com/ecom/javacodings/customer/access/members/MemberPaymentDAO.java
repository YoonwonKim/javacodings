package com.ecom.javacodings.customer.access.members;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.MemberPaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberPaymentDAO {
    MemberPaymentDTO findByMemberId(@Param("member_id") String memberId);
}
