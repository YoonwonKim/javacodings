package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.common.transfer.table.BannerDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;

@Service
public interface CustomerService {
    // Region 회원 정보 관리 메소드
    MemberDTO login(MemberDTO member);
    // End Region 회원 정보 관리 메소드
    // Region 제품 및  이벤트 정보 관리 메소드
    List<BannerDTO> listMain(int number);
    List<BannerDTO> listEvent();
    Map<String, Object> listNew(int number);
    Map<String, Object> listBest(int number);
    Map<String, Object> listItemsByTagId(String tagId);
    // End Region 제품 및  이벤트 정보 관리 메소드
    String searchId(MemberDTO member);
	int updatePasswd(MemberDTO member);
    
    //회원가입
    int memberJoin(MemberDTO mdto);
	int idCheck(String member_id);
}