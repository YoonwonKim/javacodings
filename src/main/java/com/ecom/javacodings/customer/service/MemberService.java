package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.identity.SequenceGenerator;
import com.ecom.javacodings.common.page.PageConstructor;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.policies.OrderPolicies;

import com.ecom.javacodings.common.transfer.BannerDTO;
import com.ecom.javacodings.common.transfer.CartDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.access.BannerDAO;
import com.ecom.javacodings.customer.access.CartDAO;
import com.ecom.javacodings.customer.access.ItemDAO;
import com.ecom.javacodings.customer.access.MemberDAO;
import com.ecom.javacodings.customer.access.OrderDAO;
import com.ecom.javacodings.external.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("memberService")
public class MemberService implements IMemberService {

    @Autowired PurchaseService payUpService;
    SequenceGenerator sequenceGenerator = new SequenceGenerator();

    // Region Member

    @Autowired MemberDAO memberDAO;

    final int TEMP_PASSWORD_LENGTH = 16;

    // ? metadata ----------------------------------------------------------------

    @Override
    public int isExistMemberId(String memberId) {
        return memberDAO.isExistMemberId(memberId);
    }

    // ? Basic CRUD --------------------------------------------------------------
    // * Create Row

    @Override
    public int addMember(MemberDTO member) {
        return memberDAO.addMember(member);
    }

    // * Read Table

    @Override
    public String getMemberIdByNameAndPhone(String name, String phone) {
        return memberDAO.getMemberIdByNameAndPhone(name, phone);
    }

    @Override
    public String getAndSetTemporaryPasswordByMemberId(String memberId) {
        String temporaryPassword = sequenceGenerator.generate(TEMP_PASSWORD_LENGTH);
        if (memberDAO.setPasswordByMemberId(temporaryPassword, memberId) > 0)
            return temporaryPassword;
        else return "failed";
    }

    @Override
    public MemberDTO findMemberByIdAndPassword(String memberId, String password) {
        return memberDAO.findMemberByIdAndPassword(memberId, password);
    }

    // * Update Row

    @Override
    public int archiveMemberByMemberId(String memberId) {
        return memberDAO.archiveMemberByMemberId(memberId);
    }

    //? Query Sub-Tables ---------------------------------------------------------
    // Member Address

    @Override
    public MemberDTO getAddressByMemberId(String memberId) {
        return memberDAO.getAddressByMemberId(memberId);
    }

    @Override
    public int editAddress(MemberDTO addressData, int priority, String memberId) {
        return memberDAO.editAddressByPriorityAndMemberId(addressData, priority, memberId);
    }
    
    @Override
    public int editAddressPriorityAndMemberId(int newPriority, int oldPriority, String memberId) {
    	return memberDAO.editPriorityOfAddressByPriorityAndMemberId(newPriority, oldPriority, memberId);
    }

    @Override
    public int deleteAddressByPriorityAndMemberId(int priority, String memberId) {
        return memberDAO.deleteAddressByPriorityAndMemberID(priority, memberId);
    }

    // Member Information

    @Override
    public int editMemberInfoByMemberId(MemberDTO memberData, String memberId) {
        return memberDAO.editMemberInfoByMemberId(memberData, memberId);
    }

    // End Region Member
    // Region Item

    @Autowired ItemDAO itemDAO;

    final int GRId_COUNT = 8;
    final int DEFAULT_PRODUCT_ROW = 15;
    PageConstructor productPageConstructor = new PageConstructor(DEFAULT_PRODUCT_ROW,
            (String criteria, PageDTO pageData) -> Collections.singletonList(itemDAO.findAllItemsByCategory(criteria, pageData)),
            (String criteria) -> itemDAO.countItemsByCategory(criteria)
    );

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public Map<String, Object> getItemPageByCategory(String category, int page) {
        productPageConstructor.setCriteria(category);
        return productPageConstructor.getPageMapOrNull(page);
    }

    @Override
    public Map<String, Object> getItemPageByCategoryWithRow(String category, int page, int row) {
        productPageConstructor.setCriteria(category);
        productPageConstructor.setRow(row);
        return productPageConstructor.getPageMapOrNull(page);
    }

    @Override
    public Map<String, Object> getItemPageOfMain(List<String> tags) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("news", itemDAO.findFewItemsOrderByRegDate(GRId_COUNT));
        resultMap.put("bestsellers", itemDAO.findFewItemsOrderByOrderCount(GRId_COUNT));

        Map<String, Object> itemListByTag = new HashMap<>();
        for(String tag : tags) {
            itemListByTag.put(tag, itemDAO.findFewItemsByTag(tag, GRId_COUNT));
        }
        resultMap.put("items", itemListByTag);
        return resultMap;
    }

    @Override
    public ItemDTO findItemByItemId(String itemId){
        return itemDAO.findItemByItemId(itemId);
    }

    // End Region Item
    // Region Events

    @Autowired BannerDAO bannerDAO;

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public List<BannerDTO> findAllBannersByCategory(String category, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("category", category);
        return bannerDAO.getBannerByCategory(params);
    }

    // End Region Events
    // Region Cart

    @Autowired CartDAO cartDAO;

    final int DEFAULT_CART_ROW = 15;
    PageConstructor cartPageConstructor = new PageConstructor(DEFAULT_CART_ROW,
            (String criteria, PageDTO pageData) -> Collections.singletonList(cartDAO.findAllCartByMemberId(criteria, pageData)),
            (String criteria) -> cartDAO.countCartByMemberId(criteria)
    );

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public Map<String, Object> getCartPageByMemberId(int page, String memberId) {
        return cartPageConstructor.getPageMapOrNull(page);
    }

    @Override
    public Map<String, Object> getCartPageByMemberIdWithRow(int page, String memberId, int row) {
        cartPageConstructor.setRow(row);
        return cartPageConstructor.getPageMapOrNull(page);
    }

    @Override
    public int addCart(CartDTO cart) {
        int quantity = cartDAO.getQuantityOfCartByItemIdAndMemberId(cart.getItem_id(), cart.getMember_id());
        if (quantity == 0)
            return cartDAO.addCart(cart);

        cart.setQuantity(quantity + cart.getQuantity());
        return cartDAO.editCartByItemIdAndMemberId(cart);
    }

    @Override
    public int deleteCartByMemberAndItemId(String memberId, String itemId) {
        return cartDAO.deleteCartByMemberIdAndItemId(memberId, itemId);
    }

    // End Region Cart
    // Region Order

    @Autowired OrderDAO orderDAO;

    //? Metadata ----------------------------------------------------------------

    @Override
    public List<OrderDTO> countOrdersByMemberId(String MemberId) {
        return orderDAO.countOrdersByMemberId(MemberId);
    }

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public int addOrder(CartDTO item) {
        String order_id = sequenceGenerator.generateUnique(
                (String generatedId) -> orderDAO.isExistOrderId(generatedId),
                OrderPolicies.ID_LENGTH.getOrderPolicies()
        );
        item.setOrder_id(order_id);
        int result = orderDAO.addOrder(item);
        result *= cartDAO.deleteCartByMemberIdAndItemId(item.getMember_id(), item.getItem_id());
        return result;
    }

    // End Region Order
}
