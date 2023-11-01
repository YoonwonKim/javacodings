package com.ecom.javacodings.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.access.ItemDAO;
import com.ecom.javacodings.customer.access.TagDAO;
import com.ecom.javacodings.customer.transfer.BannerDTO;
import com.ecom.javacodings.customer.transfer.ItemDTO;
import com.ecom.javacodings.customer.transfer.TagDTO;

@Service("ItemService")
public class ItemService implements CustomerService {
	
	@Autowired
	ItemDAO itemDAO;
	
	@Autowired
	TagDAO tagDAO;

	@Override
	public MemberDTO login(MemberDTO member) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BannerDTO> getbanner(BannerDTO banner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BannerDTO> listEvent(BannerDTO banner) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ItemDTO> listNew(ItemDTO item) {
		return itemDAO.listNew(item);
	}

	
	@Override
    public List<ItemDTO> listBest(ItemDTO item) {


        return itemDAO.listBest(item);
    }

    @Override
    public Map<String, Object> listTagById(String tagId) {
    	Map<String, Object> reSet = new HashMap<String, Object>();
    	
    	List<ItemDTO> listMd = itemDAO.listTagById();
    	TagDTO tag = (TagDTO)tagDAO.getLabel(tagId);
    	
    	reSet.put("listMd" , listMd);
    	reSet.put("label", tag.getLabel());
    	reSet.put("desc", tag.getDesc());
    	
    	return reSet;
    }


	

}
