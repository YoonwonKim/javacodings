package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
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
    int temporaryPassword(MemberDTO member);

    //회원가입
    int memberJoin(MemberDTO mdto);
	int idCheck(String member_id);
    Map<String, Object> getListItem(PageDTO pageDTO);
    ItemDTO listItemDt(ItemDTO item_id);

    int setOrder(OrderDTO order);
    OrderDTO getOrder(OrderDTO order);
	
	//카테고리
	
    List<ItemDTO> listProductsInCategory(PageDTO page, String category);

    int countProductsInCategory(String category);
}