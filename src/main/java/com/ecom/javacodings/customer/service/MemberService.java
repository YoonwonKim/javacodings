package com.ecom.javacodings.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ecom.javacodings.common.transfer.table.*;
import com.ecom.javacodings.customer.access.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.PageDTO;

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

    @Autowired
    OrderDAO orderDAO;

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
}
