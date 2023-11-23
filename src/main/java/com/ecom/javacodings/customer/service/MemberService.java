package com.ecom.javacodings.customer.service;

import com.ecom.javacodings.common.transfer.table.*;
import com.ecom.javacodings.customer.access.BannerDAO;
import com.ecom.javacodings.customer.access.ItemDAO;
import com.ecom.javacodings.customer.access.MemberDAO;
import com.ecom.javacodings.customer.access.TagDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
