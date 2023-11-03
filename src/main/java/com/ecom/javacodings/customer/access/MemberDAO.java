package com.ecom.javacodings.customer.access;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.transfer.BannerDTO;
import com.ecom.javacodings.customer.transfer.ItemDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {
    MemberDTO login(MemberDTO member);
    
    int register(MemberDTO member);


}
