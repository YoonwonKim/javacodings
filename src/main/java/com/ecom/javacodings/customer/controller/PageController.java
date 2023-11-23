package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/product/{item_id}")
    public String viewProduct(HttpServletRequest request, HttpServletResponse response, Model model,
					   ItemDTO item) {
		HttpSession session = request.getSession();
		MemberDTO member = new MemberDTO();
		member.setMember_id("asdf");
		member.setPassword("LhGDDTI81");
		session.setAttribute("ssKey", memberService.login(member));


    	item = memberService.listItemDt(item);
    	model.addAttribute("item", item);

    	return "customer/product-view";
    }
}
