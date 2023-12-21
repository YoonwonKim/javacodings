package com.ecom.javacodings.customer.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.identity.SequenceGenerator;
import com.ecom.javacodings.common.page.PageConstructor;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.policies.OrderPolicies;
import com.ecom.javacodings.common.transfer.CartDTO;
import com.ecom.javacodings.common.transfer.EventBannerDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberAddressDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.access.BannerDAO;
import com.ecom.javacodings.customer.access.events.EventDAO;
import com.ecom.javacodings.customer.access.items.ItemDAO;
import com.ecom.javacodings.customer.access.members.CartDAO;
import com.ecom.javacodings.customer.access.members.MemberAddressDAO;
import com.ecom.javacodings.customer.access.members.MemberDAO;
import com.ecom.javacodings.customer.access.members.MemberInfoDAO;
import com.ecom.javacodings.customer.access.members.MemberPaymentDAO;
import com.ecom.javacodings.customer.access.orders.OrderDAO;
//import com.ecom.javacodings.external.purchase.PurchaseService;
import com.ecom.javacodings.purchase.service.IPurchaseService;

@Service("memberService")
public class MemberService implements IMemberService {

    @Autowired
    IPurchaseService payUpService;
    SequenceGenerator sequenceGenerator = new SequenceGenerator();    
    
    // Region Member

    @Autowired MemberDAO memberDAO;
    @Autowired MemberInfoDAO memberInfoDAO;
    @Autowired MemberAddressDAO addressDAO;
    @Autowired MemberPaymentDAO paymentDAO;

    final int TEMP_PASSWORD_LENGTH = 16;

    // ? metadata ----------------------------------------------------------------

    @Override
    public Boolean isExistMemberId(String memberId) {
        return memberDAO.isExistMemberId(memberId);
    }

    // ? Basic CRUD --------------------------------------------------------------
    // * Create Row

    @Override
    public int addMember(MemberDTO member, MemberAddressDTO address) {
        int result = 0;
        result += memberDAO.add(member);
        result += addressDAO.add(address);

        return result;
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
        return memberDAO.findByIdAndPassword(memberId, password);
    }

    // * Update Row

    @Override
    public int archiveMemberByMemberId(String memberId) {
        return memberDAO.archiveByMemberId(memberId);
    }

    //? Query Sub-Tables ---------------------------------------------------------
    // Member Address

    @Override
    public MemberAddressDTO getPrimaryAddress(String memberId) {
        return addressDAO.findPrimaryByMemberId(memberId);
    }

    @Override
    public List<MemberAddressDTO> getAddressByMemberId(String memberId) {
        return addressDAO.findAllByMemberId(memberId);
    }

    @Override
    public int editAddress(MemberAddressDTO oldAddressData, int priority, String memberId) {
        return addressDAO.editByPriorityAndMemberId(oldAddressData, priority, memberId);
    }
    
    @Override
	public int addAddress(MemberAddressDTO addressData, String memberId) {
    	return addressDAO.addMemberAddress(addressData, memberId);
	}
    @Override
    public int editAddressPriorityAndMemberId(int newPriority, int oldPriority, String memberId) {
    	return addressDAO.setPriorityByPriorityAndMemberId(newPriority, oldPriority, memberId);
    }

    @Override
    public int deleteAddressByPriorityAndMemberId(int priority, String memberId) {
        return addressDAO.deleteByPriorityAndMemberID(priority, memberId);
    }

    // Member Information

    @Override
    public int editMemberInfoByMemberId(MemberDTO memberData, String memberId) {
        return memberInfoDAO.editMemberInfoByMemberId(memberData, memberId);
    }

    // End Region Member
    // Region Item

    @Autowired ItemDAO itemDAO;

    final int GRID_COUNT = 8;
    final int DEFAULT_PRODUCT_ROW = 15;
    PageConstructor productPageConstructor = new PageConstructor(DEFAULT_PRODUCT_ROW,
            (String criteria, PageDTO pageData) -> Collections.singletonList(itemDAO.findAllByCategory(criteria, pageData)),
            (String criteria) -> itemDAO.countByCategory(criteria)
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
        resultMap.put("news", itemDAO.findAllByRegDate(GRID_COUNT));
        resultMap.put("bestsellers", itemDAO.findAllByOrderCount(GRID_COUNT));

        Map<String, Object> itemListByTag = new HashMap<>();
        for(String tag : tags) {
            itemListByTag.put(tag, itemDAO.findAllByTag(tag, GRID_COUNT));
        }
        resultMap.putAll(itemListByTag);
        return resultMap;
    }

    @Override
    public ItemDTO findItemByItemId(String itemId){
        return itemDAO.findByItemId(itemId);
    }

    // End Region Item
    // Region Events

    @Autowired BannerDAO bannerDAO;

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public List<EventBannerDTO> findAllBannersByCategory(String category, int limit) {
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
    
    @Autowired
    EventDAO eventDAO;
    
    final int DEFAULT_EVENT_ROW = 30;
    PageConstructor eventPageConstructor = new PageConstructor(DEFAULT_PRODUCT_ROW,
    		(String criteria, PageDTO pageData) -> Collections.singletonList(eventDAO.findAll(pageData)),
    		(String criteria) -> eventDAO.count()
    		);
    
    @Override
    public void setEventPageRow(int row) { eventPageConstructor.setRow(row); }
    	
   
    @Override
    public Map<String, Object> getEventPageMap(int currentPage){
    	Map<String, Object> resultMap = eventPageConstructor.getPageMapOrNull(currentPage);
    	if (resultMap == null) resultMap.put("responseMsg", "outboundError");
    	return resultMap;
    }
    
	/* todo 확인후 지움
	 * @Override public String getItemsByEventId(String eventId) { List<String>
	 * itemList = eventDAO.getAllItemLabelByEventId(eventId); ObjectMapper mapper =
	 * new ObjectMapper(); String result = ""; try { result =
	 * mapper.writeValueAsString(itemList); result = result.replace("[", ""); result
	 * = result.replace("]", ""); result = result.replace("\"", ""); result =
	 * result.replace(",", ", ");
	 * 
	 * } catch (JsonProcessingException e) { } return result; }
	 */

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public Map<String, Object> getCartPageByMemberId(int page, String memberId) {
        cartPageConstructor.setCriteria(memberId);
        return cartPageConstructor.getPageMapOrNull(page);
    }

    @Override
    public Map<String, Object> getCartPageByMemberIdWithRow(int page, String memberId, int row) {
        cartPageConstructor.setRow(row);
        cartPageConstructor.setCriteria(memberId);
        return cartPageConstructor.getPageMapOrNull(page);
    }

    @Override
    public int addCart(CartDTO cart) {
        Integer amount = cartDAO.getQuantityByItemIdAndMemberId(cart.getItem_id(), cart.getMember_id());
        if (amount == null || amount == 0)
            return cartDAO.addCart(cart);

        cart.setAmount(amount + cart.getAmount());
        return cartDAO.editCartByItemIdAndMemberId(cart);
    }

    @Override
    public int deleteCartByMemberAndItemId(String memberId, String itemId) {
        return cartDAO.deleteByMemberIdAndItemId(memberId, itemId);
    }

    // End Region Cart
    // Region Order

    @Autowired OrderDAO orderDAO;

    //? Metadata ----------------------------------------------------------------

    @Override
    public List<OrderDTO> countOrdersByMemberId(String MemberId) {
        return orderDAO.countOrdersByMemberId(MemberId);
    }
    
    @Override
	public List<OrderDTO> findAllByMemberOrderOrders(String MemberId) {
		return orderDAO.findAllByMemberOrderOrders(MemberId);
	}
    
    @Override
    public List<CartDTO> findAllByMemberOrderItems(String memberId, String orderId) {
    	return orderDAO.findAllByMemberOrderItems(memberId, orderId);
    }
    
    @Override
	public List<OrderDTO> findOrderItemsByOrderId(String orderId) {
		return orderDAO.findOrderItemsByOrderId(orderId);
	}

	@Override
	public List<ItemDTO> findItemsByOrderId(String orderId) {
		return orderDAO.findItemsByOrderId(orderId);
	}

    //? Basic CRUD --------------------------------------------------------------

    @Override
    public OrderDTO findOrderByOrderId(String orderId) {
        return orderDAO.findUnPayedOrderByOrderId(orderId);
    }

    @Override
    public int successPurchase(String orderId, String regDate) {
        List<String> itemList = orderDAO.getAllItemIdByOrderId(orderId);
        int result = 1;
        for (String itemId : itemList)
            result *= itemDAO.decreaseStockByItemId(itemId);
        result *= orderDAO.increaseStateByOrderId(orderId, regDate);
        return result;
    }

    @Override
    public int setTransactionIdByOrderId(String transactionId, String orderId) {
        return orderDAO.setTransactionIdByOrderId(transactionId, orderId);
    }

    @Override
    public OrderDTO addOrder(String memberId, List<CartDTO> cartList) {
        int result = 1;
        int amount = 0;
        for(CartDTO cart : cartList) {
            amount += cart.getAmount();
        }

        String generatedOrderId = sequenceGenerator.generateUnique(
                (String generatedId) -> orderDAO.isExistOrderId(generatedId),
                OrderPolicies.ID_LENGTH.getOrderPolicies()
        );

        OrderDTO orderData = new OrderDTO();
        orderData.setOrder_id(generatedOrderId);
        orderData.setMember_id(memberId);
        orderData.setItemList(cartList);
        orderData.setAmount(amount);
        result *= orderDAO.add(orderData);

        if (result != 0) { return orderData; }
        return null;
    }

    @Override
    public List<CartDTO> findAllCartByOrderId(String orderId) {
        return orderDAO.findAllCartByOrderId(orderId);
    }

    @Override
    public List<ItemDTO> findAllItemsByOrderId(String orderId) {
        return orderDAO.findAllItemsByOrderId(orderId);
    }
    
	@Override
	public String getItemsByEventId(String eventId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EventBannerDTO> getBannerImageByEventId(String eventId) {
    	List<EventBannerDTO> result = eventDAO.mainBanner(eventId);
    	return result;	
	}
	
	@Override
	public List<ItemDTO> getAllItemsByEventId(String eventId) {
		List<ItemDTO> result = eventDAO.eventItem(eventId);
		return result;
	}

    @Override
    public MemberDTO findMemberByMemberIdAndName(String memberId, String memberName) {
        return memberDAO.findByIdAndName(memberId, memberName);
    }



    @Override
    public int removeCartByOrderId(String memberId, String orderId) {
        List<ItemDTO> itemList = orderDAO.findAllItemsByOrderId(orderId);
        return cartDAO.deleteAllByMemberIdAndItemId(memberId, itemList);
    }


    // End Region Order
}
