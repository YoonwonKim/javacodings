package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.customer.access.BannerDAO;
import com.ecom.javacodings.customer.access.ItemDAO;
import com.ecom.javacodings.customer.access.MemberDAO;
import com.ecom.javacodings.customer.access.OrderDAO;
import com.ecom.javacodings.customer.access.TagDAO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.BannerDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecom.javacodings.common.transfer.table.TagDTO;
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
    public Map<String, Object> cartLists(PageDTO page) {    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	List<OrderDTO> cartList = orderDAO.cartLists(page);
    	
    	params.put("cartLists", cartList);
    	return params;    	
    }

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
}
