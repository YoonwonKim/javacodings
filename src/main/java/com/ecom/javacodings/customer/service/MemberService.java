package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.*;
import com.ecom.javacodings.customer.access.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService implements CustomerService {
    @Autowired
    MemberDAO memberDAO;

    // Region Pages
    @Override
    public MemberDTO login(MemberDTO member) {
        return memberDAO.login(member);
    }
    // End Region Pages
    // Region BannerService
    @Autowired
    BannerDAO bannerDAO;

    @Override
    public List<BannerDTO> listMain(int number) {
        return bannerDAO.listMain(number);
    }

    @Override
    public List<BannerDTO> listEvent() {
        return bannerDAO.listEvent();
    }
    // End Region BannerService
    // Region ItemService
    @Autowired
    ItemDAO itemDAO;

    @Autowired
    TagDAO tagDAO;
    
    @Autowired
    OrderDAO orderDAO;

    @Override
    public Map<String, Object> listNew(int number) {
        Map<String, Object> result = new HashMap<>();
        result.put("itemList", itemDAO.listNew(number));
        result.put("label", "NEWS");
        result.put("desc",  "지금 핫한 신상품");
        return result;
    }
    @Override
    public Map<String, Object> listBest(int number) {
        Map<String, Object> result = new HashMap<>();
        result.put("itemList", itemDAO.listBest(number));
        result.put("label", "BEST");
        result.put("desc",  "이유있는 인기 아이템");
        return result;
    }
    @Override
    public Map<String, Object> listItemsByTagId(String tagId) {
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Object> params = new HashMap<>();
        List<ItemDTO> listMd = itemDAO.listItemsByTagId(tagId);
        TagDTO tag = tagDAO.getTag(tagId);

        result.put("itemList" , listMd);
        result.put("label", tag.getLabel());
        result.put("desc", tag.getDesc());
        return result;
    }
    // End Region ItemService
    //장바구니 시작
	@Override
	public List<OrderDTO> updateCart(List<OrderDTO> orderList) {
		return orderDAO.updateCart(orderList);
	}

	@Override
	public List<OrderDTO> deleteOrdersByCart(List<OrderDTO> orderList) {
		return orderDAO.deleteOrdersByCart(orderList);
	}
	
	@Override
	public List<OrderDTO> deleteOrderStateByCart(List<OrderDTO> orderList) {
		return orderDAO.deleteOrderStateByCart(orderList);
	}

    //장바구니 끝

	@Override
	public String searchId(MemberDTO member) {
		return memberDAO.searchId(member);
	}

    @Override
    public int temporaryPassword(MemberDTO member) {
        return memberDAO.temporaryPassword(member);
    }

    //회원가입
    @Override
    public int memberJoin(MemberDTO mdto) {
    	System.out.println(mdto);
    	return memberDAO.memberJoin(mdto);
    }

	@Override
	public int idCheck(String member_id) {
		return memberDAO.idCheck(member_id);
	}
	
	//카테고리

	@Override
	public int updateMembers(MemberDTO member) {
		return memberDAO.updateMembers(member);
	}

	@Override
	public int updateMemberInfos(MemberDTO member) {
		return memberDAO.updateMemberInfos(member);
	}

	@Override
	public int updateAddress(MemberDTO member) {
		return memberDAO.updateAddress(member);
	}

	@Override
	public int deleteMembers(MemberDTO member) {
		return memberDAO.deleteMembers(member);
	}
	
	@Override
	public int deleteMemberInfos(MemberDTO member) {
		return memberDAO.deleteMemberInfos(member);
	}
	
	@Override
	public int deleteAddress(MemberDTO member) {
		return memberDAO.deleteAddress(member);
	}

    @Override
    public MemberDTO getMemberById(MemberDTO member) {
        return memberDAO.getMemberById(member);
    }

    @Override
    public MemberDTO getCurrentAddress(MemberDTO member) {
        return memberDAO.getCurrentAddress(member);
    }

    @Override
    public List<OrderDTO> countMemberOrders(MemberDTO member) {
        List<OrderDTO> result = memberDAO.countMemberOrders(member);
        return result;
    }

    // Region Cart

    @Autowired CartDAO cartDAO;

    @Override
    public List<CartDTO> listCart(PageDTO pageSet, String member_id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", pageSet);
        params.put("member_id", member_id);

        return cartDAO.listCart(params);
    }

    @Override
    public int countCart(String member_id) {
        return cartDAO.countCart(member_id);
    }



    //장바구니 시작
    @Override
    public List<ItemDTO> listProductsInCategory(PageDTO page, String category) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", page);
        params.put("category", category);

        return itemDAO.listProductsInCategory(params);
    }

    @Override
    public int countProductsInCategory(String category) {
        return itemDAO.countProductsInCategory(category);
    }
    
    //아이템 리스트 받아오기
    @Override
    public Map<String, Object> getListItem(PageDTO pageDTO){
    	
    	int cnt = itemDAO.getItemCnt();
    	Map<String, Object> reSet = new HashMap<String, Object>();
    	List<ItemDTO> iList = itemDAO.getListItem();
    	reSet.put("cnt", cnt);
    	reSet.put("iList", iList);
    	return reSet;
    }
    
    @Override
    public ItemDTO listItemDt(ItemDTO itemDTO){
    	return itemDAO.listItemDt(itemDTO);
    	
    	
    }

    @Override
    public int setOrder(OrderDTO order) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();
        String randomOrderID = "";
        OrderDTO checkDuplicate = new OrderDTO();

        do {
            randomOrderID = random.ints(leftLimit,rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            order.setOrder_id(randomOrderID);
            checkDuplicate = getOrder(order);
        } while ( checkDuplicate != null );

        return orderDAO.setOrder(order);
    }

    @Override
    public OrderDTO getOrder(OrderDTO order) {
        return orderDAO.getOrder(order);
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
    public int order(CartDTO item) {
        int result = 0;

        // SET ORDER
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();
        String randomOrderID = "";
        OrderDTO checkDuplicate = new OrderDTO();
        do {
            randomOrderID = random.ints(leftLimit,rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            item.setOrder_id(randomOrderID);
            checkDuplicate = getOrder(item);
        } while ( checkDuplicate != null );

        result += orderDAO.insertOrder(item);
        result *= cartDAO.deleteCart(item);

        return result;
    }

    @Override
    public int deleteCart(CartDTO cart) {
        return cartDAO.deleteCart(cart);
    }

    @Override
    public int cart(CartDTO cart) {
        Integer quantity = cartDAO.getCart(cart);
        if (quantity == null)
            return cartDAO.insertCart(cart);

        cart.setQuantity(quantity + cart.getQuantity());
        return cartDAO.updateCart(cart);
    }
}
