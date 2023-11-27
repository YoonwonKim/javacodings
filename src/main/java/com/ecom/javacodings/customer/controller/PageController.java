package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.page.PageConstructor;
import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    CustomerService memberService;

	PageConstructor pageConstructor = new PageConstructor();

	// End Region Variables

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
	
	@RequestMapping("account/search")
	public String searchMember() {
		return "customer/account/search";
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

	@RequestMapping("/product/c/{category}")
	public String getcategorylist(HttpServletRequest request, HttpServletResponse response,
			                      Model model, @PathVariable("category") String category) {
		model.addAttribute("category", category.toUpperCase());
		return "customer/category";
	}
	
	// Region Account

	@GetMapping("/account/{tab}")
	public String accountTab(@PathVariable("tab") String tab) {
		String result = "customer/account/" + tab;
		return result;
	}

	// End Region Account
	// Region Product

    @GetMapping("/product/{item_id}")
    public String viewProduct(HttpServletRequest request, HttpServletResponse response, Model model,
					   ItemDTO item) {
    	item = memberService.listItemDt(item);
    	model.addAttribute("item", item);

    	return "customer/product-view";
    }

	// End Region Product
	// Region Cart

	@RequestMapping("/cart")
	public String orderItems(HttpServletRequest request, HttpServletResponse response,
							 Model model) {
		// 로그인 정보 확인
		HttpSession session = request.getSession();
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey == null) return "redirect:/account/login";
		String member = ssKey.getMember_id();

		// 페이지 구성
		Map<String, Object> pageMap = pageConstructor.getPages(
				(PageDTO pageSet) -> Collections.singletonList(memberService.listCart(pageSet, member)),
				request.getParameter("page"),
				request.getParameter("row"),
				memberService.countCart(member)
		);

		if((Integer) pageMap.get("currentPage") > (Integer) pageMap.get("totalPages")) {
			return "redirect:/cart?page=" + pageMap.get("totalPages") + "&row=" + pageMap.get("row");
		}

		// 페이지 반환
		model.addAllAttributes(pageMap);
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

