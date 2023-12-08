package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.page.PageConstructor;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberAddressDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.service.IMemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	// Region Variables
	
    @Autowired
	IMemberService memberService;

	// End Region Variables

    @RequestMapping()
    public String main(HttpServletRequest request, HttpServletResponse response,
                       Model model) {
		//? Session
		HttpSession session = request.getSession();
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		model.addAttribute("ssKey", ssKey);

		//? Infos
    	model.addAttribute("mainList",  memberService.findAllBannersByCategory("main", 8));
		model.addAttribute("eventList", memberService.findAllBannersByCategory("event", 3));
		// Tag List
		List<String> tagList = List.of(new String[]{"players", "mascots", "fashion"});
		Map<String, Object> mdList = memberService.getItemPageOfMain(tagList);
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
    public String register(HttpServletRequest request, HttpServletResponse response,
    					MemberDTO mdto, Model model) {
    	return "customer/account/register";
    }
	
	@RequestMapping("account/search")
	public String findAccount() {
		return "customer/account/search";
	}

	// Region Account

	@RequestMapping("/account")
	public String information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("ssKey");

		if (member == null) return "redirect:/account/login";
		else {
			member = memberService.findMemberByIdAndPassword(
					member.getMember_id(), member.getPassword());
			member.setPassword(null); // Hide password for safe
			model.addAttribute("ssKey", member);

			MemberAddressDTO address = memberService.getPrimaryAddress(member.getMember_id());
			model.addAttribute("address", address);

			List<OrderDTO> countMemberOrders = memberService.countOrdersByMemberId(member.getMember_id());
			model.addAttribute("countMemberOrders", countMemberOrders);
		}

		return "customer/account/information";
	}

	@GetMapping("/account/{tab}")
	public String accountTab(@PathVariable("tab") String tab) {
		String result = "customer/account/" + tab;
		return result;
	}

	// End Region Account
	// Region Product

	@RequestMapping("/product/c/{category}")
	public String getcategorylist(HttpServletRequest request, HttpServletResponse response,
								  Model model, @PathVariable("category") String category) {
		model.addAttribute("category", category.toUpperCase());
		return "customer/category";
	}

    @GetMapping("/product/{item_id}")
    public String viewProduct(Model model, @PathVariable("item_id") String itemId) {
    	ItemDTO item = memberService.findItemByItemId(itemId);
    	model.addAttribute("item", item);

    	return "customer/product-view";
    }

	// End Region Product
	// Region Cart

	@RequestMapping("/cart")
	public String orderItems(HttpServletRequest request,
							 String page, String row, Model model) {
		HttpSession session = request.getSession();
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey == null) return "redirect:/account/login";

		page = (page == null) ? "1" : page;
		int currentPage = Integer.parseInt(page);
		row  = (row == null)  ? "20" : row;
		int currentRow  = Integer.parseInt(row);
		Map<String, Object> resultMap = memberService.getCartPageByMemberIdWithRow(currentPage, ssKey.getMember_id(), currentRow);

//		if (resultMap == null) return "redirect:/cart?page=" + (currentPage - 1) + "&row=" + currentRow;
		model.addAllAttributes(resultMap);
		return "customer/cart";
	}

	// End Region Cart
	// Region Order

    @RequestMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response,
    		Model model, PageDTO page) {
    	return "index";
    }

	// End Region Order
}

