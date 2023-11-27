package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import com.ecom.javacodings.common.transfer.*;
import com.ecom.javacodings.common.page.PageDTO;
import org.springframework.stereotype.Service;

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
    //장바구니 끝
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
	//회원정보(수정, 탈퇴)
	int updateMembers(MemberDTO member);
	int updateMemberInfos(MemberDTO member);
	int updateAddress(MemberDTO member);
	int deleteMembers(MemberDTO member);
	int deleteMemberInfos(MemberDTO member);
	int deleteAddress(MemberDTO member);

    MemberDTO getMemberById(MemberDTO member);

    MemberDTO getCurrentAddress(MemberDTO member);

    List<OrderDTO> countMemberOrders(MemberDTO member);
    // Region Cart

    List<CartDTO> listCart(PageDTO pageSet, String member_id);
    int countCart(String member_id);

    List<OrderDTO> updateCart(List<OrderDTO> orderList);
    List<OrderDTO> deleteOrdersByCart(List<OrderDTO> orderList);
    List<OrderDTO> deleteOrderStateByCart(List<OrderDTO> orderList);

    int order(CartDTO item);
    int deleteCart(CartDTO item);

    int cart(CartDTO cart);

    // End Region Cart
}