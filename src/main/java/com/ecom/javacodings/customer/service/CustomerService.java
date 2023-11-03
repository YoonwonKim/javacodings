package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.transfer.BannerDTO;
import com.ecom.javacodings.customer.transfer.ItemDTO;

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
	List<ItemDTO> listItem(int item);
	int updateList(ItemDTO item);
	int deleteItem(ItemDTO item);
	//RQ - 011 - 05 재고 데이터 변경
	int updateStockItem(ItemDTO item);
	//RQ - 012 - 02 상품관리페이지 제품 정보
	int listItemByItemId(ItemDTO item);
	//RQ - 011 - 06 가격 데이터 변경
	int updatePrice(ItemDTO item);
	//회원가입
	int register(MemberDTO member);
	//상품담기
    int insertOrder(ItemDTO item);
    //상품 상태변경
    int updateOrderState(ItemDTO item);

    
	 

}
