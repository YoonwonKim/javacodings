package com.ecom.javacodings.customer.service;

import java.util.List;
import java.util.Map;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.*;

import org.springframework.stereotype.Service;

@Service
public interface IMemberService {
    Boolean isExistMemberId(String memberId);

    int addMember(MemberDTO member, MemberAddressDTO address);

    String getMemberIdByNameAndPhone(String name, String phone);

    String getAndSetTemporaryPasswordByMemberId(String memberId);

    MemberDTO findMemberByIdAndPassword(String memberId, String password);

    int archiveMemberByMemberId(String memberId);

    List<MemberAddressDTO> getAddressByMemberId(String memberId);

    int editAddress(MemberAddressDTO oldAddressData, int priority, String memberId);
    
    int addAddress(MemberAddressDTO addressData, String memberId);

    int editAddressPriorityAndMemberId(int newPriority, int oldPriority, String memberId);

    int deleteAddressByPriorityAndMemberId(int priority, String memberId);

    int editMemberInfoByMemberId(MemberDTO memberData, String memberId);

    Map<String, Object> getItemPageByCategory(String category, int page);

    
    Map<String, Object> getItemPageByCategoryWithRow(String category, int page, int row);

    Map<String, Object> getItemPageOfMain(List<String> tags);

    ItemDTO findItemByItemId(String itemId);

    List<EventBannerDTO> findAllBannersByCategory(String category, int limit);

    Map<String, Object> getCartPageByMemberId(int page, String memberId);

    Map<String, Object> getCartPageByMemberIdWithRow(int page, String memberId, int row);

    int addCart(CartDTO cart);

    int deleteCartByMemberAndItemId(String memberId, String itemId);

    List<OrderDTO> countOrdersByMemberId(String MemberId);

    MemberAddressDTO getPrimaryAddress(String memberId);

    OrderDTO findOrderByOrderId(String orderId);

    int successPurchase(String orderId, String regDate);

    int setTransactionIdByOrderId(String transactionId, String orderId);

    OrderDTO addOrder(String memberId, List<CartDTO> cartList);

    List<CartDTO> findAllCartByOrderId(String orderId);

    List<ItemDTO> findAllItemsByOrderId(String orderId);
    MemberAddressDTO getPrimaryAddress(String memberId);
    
    void setEventPageRow(int row);
    
    Map<String, Object> getEventPageMap(int currentPage);
    
    String getItemsByEventId(String eventId);

	
  List<EventBannerDTO> mainBanner(EventBannerDTO eventBannerDTO);
  
  List<ItemDTO> eventItem(EventBannerDTO eventBannerDTO);
	  
  
	 
    
    //Map<String, Object> getEventItemList(PageDTO page);
    
}