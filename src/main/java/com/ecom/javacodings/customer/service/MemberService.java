package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.identity.SequenceGenerator;
import com.ecom.javacodings.common.page.PageConstructor;
import com.ecom.javacodings.common.policies.OrderPolicies;
import com.ecom.javacodings.common.transfer.*;
import com.ecom.javacodings.customer.access.*;

import java.util.*;

import com.ecom.javacodings.external.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService implements CustomerService {

    // ? Services
    @Autowired PurchaseService payUpService;
    @Autowired MemberDAO memberDAO;

    // ? Constructors
    SequenceGenerator sequenceGenerator = new SequenceGenerator();
    PageConstructor   pageConstructor   = new PageConstructor();

    // Region Member

    // ? metadata ----------------------------------------------------------------

    @Override
    public int isExistMemberID(String memberID) {
        return memberDAO.isExistMemberID(memberID);
    }

    // ? Basic CRUD --------------------------------------------------------------
    // * Create Row

    @Override
    public int addMember(MemberDTO member) {
        return memberDAO.addMember(member);
    }

    // * Read Table

    @Override
    public String getMemberIDByNameAndPhone(MemberDTO member) {
        return memberDAO.getMemberIDByNameAndPhone(member);
    }

    @Override
    public String getAndSetTemporaryPasswordByMemberID(MemberDTO member) {
        String temporaryPassword = sequenceGenerator.generate(16);
        if (memberDAO.setPassword(temporaryPassword) > 0)
            return temporaryPassword;
        else return "failed";
    }

    @Override
    public MemberDTO findMemberByIDAndPassword(MemberDTO member) {
        return memberDAO.findMemberByIDAndPassword(member);
    }

    // * Update Row

    @Override
    public int archiveMemberByMemberID(String memberID) {
        return memberDAO.archiveMemberByMemberID(memberID);
    }

    //? Query Sub-Tables ---------------------------------------------------------
    // Member Address

    @Override
    public MemberDTO getAddressByMemberID(String memberID) {
        return memberDAO.getAddressByMemberID(memberID);
    }

    @Override
    public int updateAddress(MemberDTO member) {
        return memberDAO.updateAddress(member);
    }

    @Override
    public int deleteAddressByPriorityAndMemberID(String memberID, String priority) {
        Map<String, Object> params = new HashMap<>();
        params.put("member_id", memberID);
        params.put("priority", priority);
        return memberDAO.deleteAddressByPriorityAndMemberID(params);
    }

    // Member Information

    @Override
    public int updateMemberInfo(MemberDTO member) {
        return memberDAO.updateMemberInfo(member);
    }

    // End Region Member
    // Region Item

    @Autowired ItemDAO itemDAO;

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public Map<String, Object> findAllItemsByTagID(String tagID, String page, String row) {
        return pageConstructor.getPages(
                (Map<String, Object> pageMap) -> Collections.singletonList(itemDAO.findAllItemsByTagID(pageMap)),
                tagID,
                page, row,
                itemDAO.countItemsByTagID(tagID)
        );
    }
    @Override
    public MemberDTO getMemberById(String member_id) {
        MemberDTO member = new MemberDTO();
        member.setMember_id(member_id);
        return memberDAO.getMemberById(member);
    }

    @Override
    public Map<String, Object> findAllItemsByCategory(String category, String page, String row) {
        return pageConstructor.getPages(
                (Map<String, Object> pageMap) -> Collections.singletonList(itemDAO.findAllItemsByCategory(pageMap)),
                category,
                page, row,
                itemDAO.countItemsByCategory(category)
        );
    }

    @Override
    public Map<String, Object> findAllItemsOrderByRegDate(String page, String row) {
        return pageConstructor.getPages(
                (Map<String, Object> pageMap) -> Collections.singletonList(itemDAO.findAllItemsOrderByRegDate(pageMap)),
                page, row,
                itemDAO.countItems()
        );
    }

    @Override
    public Map<String, Object> findAllItemsOrderByOrderCount(String page, String row) {
        return pageConstructor.getPages(
                (Map<String, Object> pageMap) -> Collections.singletonList(itemDAO.findAllItemsOrderByOrderCount(pageMap)),
                page, row,
                itemDAO.countItems()
        );
    }

    @Override
    public ItemDTO findItemByItemID(String itemID){
        return itemDAO.findItemByItemID(itemID);
    }

    // End Region Item
    // Region Banner

    @Override
    public Integer getQuantity(CartDTO cart) {
        return cartDAO.getCart(cart);
    }
    @Autowired BannerDAO bannerDAO;

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public List<BannerDTO> findAllBannersByCategory(String category, int limit) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit", limit);
        params.put("category", category);
        return bannerDAO.getBannerByCategory(params);
    }

    // End Region Banner
    // Region Cart

    @Autowired CartDAO cartDAO;

    //? metadata ----------------------------------------------------------------

    @Override
    public int countCartByMemberID(String memberID) {
        return cartDAO.countCartByMemberID(memberID);
    }

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public Map<String, Object> findAllCartByMemberID(String memberID, String page, String row) {
        return pageConstructor.getPages(
                (Map<String, Object> pageMap) -> Collections.singletonList(cartDAO.findAllCartByMemberID(pageMap)),
                memberID,
                page, row,
                cartDAO.countCartByMemberID(memberID)
        );
    }

    @Override
    public int addCart(CartDTO cart) {
        Integer quantity = cartDAO.getQuantityByItemIDAndMemberID(cart);
        if (quantity == null)
            return cartDAO.addCart(cart);

        cart.setQuantity(quantity + cart.getQuantity());
        return cartDAO.updateCart(cart);
    }

    @Override
    public int deleteCartByMemberAndItemID(CartDTO cart) {
        return cartDAO.deleteCartByMemberIDAndItemID(cart);
    }
//	@Override
//	public List<OrderDTO> deleteOrdersByCart(List<OrderDTO> orderList) {
//		return orderDAO.deleteOrdersByCart(orderList);
//	}
	
//	@Override
//	public List<OrderDTO> deleteOrderStateByCart(List<OrderDTO> orderList) {
//		return orderDAO.deleteOrderStateByCart(orderList);
//	}
	//장바구니 끝
    @Override
    public CartDTO order(CartDTO item) {
        int result = 0;

    // End Region Cart
    // Region Order

        //? Transaction
        MemberDTO member = getMemberById(item.getMember_id());
        ItemDTO item_info = new ItemDTO();
        item_info.setItem_id(item.getItem_id());
        item_info = listItemDt(item_info);
        try {
            String transactionID = payUpService.requestPurchase(item, item_info, member);
            item.setTransaction_id(transactionID);
    @Autowired OrderDAO orderDAO;

            orderDAO.insertOrder(item);
            cartDAO.deleteCart(item);
        } catch (Exception e) {}
        return item;
    }
    //? Metadata ----------------------------------------------------------------

    @Override
    public List<OrderDTO> countOrdersByMemberID(String MemberID) {
        return orderDAO.countOrdersByMemberID(MemberID);
    }

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public int addOrder(CartDTO item) {
        String order_id = sequenceGenerator.generateUnique(
                (String generatedId) -> orderDAO.isDuplicatedID(generatedId),
                OrderPolicies.ID_LENGTH.getOrderPolicies()
        );
        item.setOrder_id(order_id);
        int result = orderDAO.addOrder(item);
        result *= cartDAO.deleteCartByMemberIDAndItemID(item);
        return result;
    }

    // End Region Order
}
