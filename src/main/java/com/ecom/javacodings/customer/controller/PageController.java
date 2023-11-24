package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class PageController {
	
    @Autowired
    CustomerService memberService;

    @RequestMapping()
    public String main(HttpServletRequest request, HttpServletResponse response,
                       Model model) {
		//? Session
		HttpSession session = request.getSession();
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		model.addAttribute("ssKey", ssKey);

		//? Infos
    	model.addAttribute("mainList", memberService.listMain(8));
    	model.addAttribute("eventList", memberService.listEvent());
		// Tag List
    	List<Map<String, Object>> mdList = new ArrayList<>();
    	mdList.add(memberService.listNew(8));
    	mdList.add(memberService.listBest(8));
    	mdList.add(memberService.listItemsByTagId("players"));
    	mdList.add(memberService.listItemsByTagId("mascots"));
    	mdList.add(memberService.listItemsByTagId("fashion"));
    	model.addAttribute("mdList", mdList);

    	return "index";
    }

	@RequestMapping("/account/login")
	public String login(HttpServletRequest request, HttpServletResponse response,
						Model model) {
		HttpSession session = request.getSession();
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey != null) return "redirect:/";
		return "customer/account/login";
	}

	@RequestMapping("/account/register")
    public String join(HttpServletRequest request, HttpServletResponse response,
    					MemberDTO mdto, Model model) {
    	return "customer/account/register";
    }

	@RequestMapping("/account")
	public String information(HttpServletRequest request, HttpServletResponse response, 
						MemberDTO member, Model model) {
		HttpSession session = request.getSession();
		member = (MemberDTO) session.getAttribute("ssKey");

		if (member == null) return "redirect:/account/login";
		else {
			member = memberService.getMemberById(member);
			model.addAttribute("ssKey", member);

			MemberDTO address = memberService.getCurrentAddress(member);
			model.addAttribute("address", address);

			List<OrderDTO> countMemberOrders = memberService.countMemberOrders(member);
			model.addAttribute("countMemberOrders", countMemberOrders);
		}

		return "customer/account/information";
	}

	@GetMapping("/account/profile")
	public String profile() {
		return "customer/account/profile";
	}
	@GetMapping("/account/location")
	public String location() {
		return "customer/account/location";
	}
	@GetMapping("/account/orders")
	public String orders() {
		return "customer/account/orders";
	}
    
    @RequestMapping("/cartLists")
    public String orderItems(HttpServletRequest request, HttpServletResponse response,
    					Model model, PageDTO page) {
        page.setStart(0);
        page.setRow(15);
        page.setEnd(page.getRow() + page.getStart());
        
        
        model.addAttribute("cartLists", memberService.cartLists(page));
    	
    	return "customer/cartlist";
    }
    
    @RequestMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response,
    		Model model, PageDTO page) {
    	
    	
    	return "index";
    }
}

