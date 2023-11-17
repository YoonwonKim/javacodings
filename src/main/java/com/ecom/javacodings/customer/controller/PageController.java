package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    CustomerService memberService;

    @RequestMapping()
    public String main(HttpServletRequest request, HttpServletResponse response,
                       Model model) {

    	model.addAttribute("mainList", memberService.listMain(8));
    	model.addAttribute("eventList", memberService.listEvent());

    	List<Map<String, Object>> mdList = new ArrayList<>();
    	mdList.add(memberService.listNew(8));
    	mdList.add(memberService.listBest(8));
    	mdList.add(memberService.listItemsByTagId("players"));
    	mdList.add(memberService.listItemsByTagId("mascots"));
    	mdList.add(memberService.listItemsByTagId("fashion"));
    	model.addAttribute("mdList", mdList);

    	return "index";
    }
    
    @RequestMapping("/listItem")
    public String listItem(HttpServletRequest request,
    					HttpServletResponse response,
    					ItemDTO idto,
    					Model model,
    					PageDTO pageDTO) {
    	
    	Map<String, Object> reSet = 
    			memberService.getListItem(pageDTO);
    	
    	model.addAttribute("cnt", reSet.get("cnt"));
    	model.addAttribute("itemList", reSet.get("iList"));
    	System.out.println("-----------iList"+ reSet);
    	return "customer/item";
    }
    
    @RequestMapping("/listItemDt")
    public String listItemDt(HttpServletRequest request,
	    					HttpServletResponse response,
	    					ItemDTO idto,
	    					Model model) {
    	
    	String page = null;
    	String itemList = null;
    	
    	ItemDTO item = memberService.listItemDt(idto);
    	
    	model.addAttribute("item", item);
    	return "customer/itemdt";	
    }
}
