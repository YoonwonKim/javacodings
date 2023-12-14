package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.CartDTO;
import com.ecom.javacodings.common.transfer.MemberAddressDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.service.IMemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ecom.javacodings.purchase.service.IPurchaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Random;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired IMemberService memberService;

    // Purchase Services
    @Autowired IPurchaseService payUpService;

    /**
     * RQ-001 로그인 기능 구현<br>
     * 비동기 통신 후 문자열 형태로 결과를 반환한다.
     * 성공 시 세션에 값을 저장한다.
     */
    @PostMapping("/account/login")
    @ResponseBody
    public String login(HttpServletRequest request, MemberDTO loginInfo) {
        HttpSession session = request.getSession();
        MemberDTO loginAttempt = memberService.findMemberByIdAndPassword(loginInfo.getMember_id(), loginInfo.getPassword());

        if (loginAttempt == null) return "failed";
        session.setAttribute("ssKey", loginAttempt);
        return "success";
    }

    /**
     * RQ-002 로그아웃 기능 구현
     * 비동기 통신 후 결과를 반환하고 세션을 초기화한다.
     */
    @PostMapping("/account/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String result = "failed";
        HttpSession session = request.getSession();
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");

        if (ssKey == null) return "success, but already logged out";
        session.removeAttribute("ssKey");
        return "success";
    }


    @PutMapping("/account/register")
    public String register(HttpServletRequest request, HttpServletResponse response,
                           MemberDTO memberData, MemberAddressDTO addressData) {
        memberData.setEmail(memberData.getEmail() + request.getParameter("email-domain"));
        memberService.addMember(memberData, addressData);

        response.setContentType("application/json");
        return "success";
    }

    
    @GetMapping("/account/search/id")
    public String searchMember(HttpServletRequest request, HttpServletResponse response,
    							MemberDTO member) {
//    	String member_id = memberService.searchId(member);
//        if (member_id == null) return "error";
//    	return member_id;
        return "";
    }

    @PostMapping("/account/search/password")
    public String updatePasswd(HttpServletRequest request, HttpServletResponse response,
    							MemberDTO member, Model model) {
        Random random = new Random();
        int leftLimit  = 48;  // numeral '0'
        int rightLimit = 122; // letter  'z'
        int targetStringLength = 9;
        String randomPassword = random.ints(leftLimit, rightLimit+1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        member.setPassword(randomPassword);
        return randomPassword;
    }
    
    @PostMapping("/account/editMemberInfoByMemberId")
    @ResponseBody
    public String editMemberInfoByMemberId(HttpServletRequest request, HttpServletResponse response,
    							@RequestBody MemberDTO member, Model model) {
    	String result = "";
    	HttpSession session = request.getSession();
    	MemberDTO memberInfo = (MemberDTO) session.getAttribute("ssKey");
    	String memberId = member.getMember_id();
    	
    	if(memberInfo == null) {
    		result = "failed";
    	} else {
    		memberService.editMemberInfoByMemberId(member, memberId);
    		session.setAttribute("ssKey", member);
    		result = "success";
    	}
    	System.out.println(member);
    	return result;
    }
    
    @PostMapping("/account/archiveByMemberID")
    @ResponseBody
    public String archiveMemberByMemberId(HttpServletRequest request, HttpServletResponse response,
    							@RequestBody MemberDTO member, Model model) {
    	String result = "";
    	HttpSession session = request.getSession();
    	MemberDTO memebrInfo = (MemberDTO) session.getAttribute("ssKey");
    	String memberId = member.getMember_id();
    	System.out.println(memberId);
    	
    	if(memebrInfo == null) {
    		result = "failed";
    	} else {
    		memberService.archiveMemberByMemberId(memberId);
    		session.setAttribute("ssKey", memebrInfo);
    		result = "success";
    	}
    	System.out.println(memebrInfo);
    	return result;
    }    
    
	@PostMapping("/account/updateDeliveryAddress")
	@ResponseBody
	public String updateDeliveryAddress(HttpServletRequest request, HttpServletResponse response,
								@RequestBody Map<String, Object> requestData, Model model) {
		String result = "";
		HttpSession session = request.getSession();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> addressDataMap = (Map<String, Object>) requestData.get("addressData");
		MemberAddressDTO addressData = objectMapper.convertValue(addressDataMap, MemberAddressDTO.class);
		
		Map<String, Object> oldAddressDataMap = (Map<String, Object>) requestData.get("oldAddressData");
		MemberAddressDTO oldAddressData = objectMapper.convertValue(oldAddressDataMap, MemberAddressDTO.class);
		
		int priority = Integer.parseInt((String) requestData.get("priority"));
		String memberId = (String) requestData.get("member_id");
		
		
		if(addressData == null) {
			result = "failed";
		} else {
			int addressRowsCreated = memberService.addAddress(addressData, memberId);
			int addressRowsUpdated = memberService.editAddress(oldAddressData, priority, memberId);
			if(addressRowsCreated > 0 && addressRowsUpdated > 0) {
				session.setAttribute("oldaddress", oldAddressData);
				if(session.getAttribute("oldaddress") != null) {
					session.setAttribute("address", addressData);
					
				}
				result = "success";                
			} else {
				result = "failed";
			}
		}
		System.out.println("Controller oldAddress==============>"+oldAddressData);
		System.out.println("Controller Address==============>"+addressData);
	    return result;
	}
    
    @PostMapping("/account/duplicate")
    public String checkDuplicate(HttpServletRequest request, HttpServletResponse response,
                                 MemberDTO member) {
        Boolean r = memberService.isExistMemberId(member.getMember_id());
        if (r) return "duplicated";
        return "not-duplicated";
    }


    // Region Products

    /*
     * JS를 이용한 무한 로딩을 구현하기 위해 REST API로 구현
     */
    @PostMapping("/product/list/{category}")
    public Object getProducts(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable("category") String category, String page, String row) {
        page = (page == null) ? "1" : page;
        Map<String, Object> resultMap = memberService.getItemPageByCategoryWithRow(category, Integer.parseInt(page), 8);

        if (resultMap == null) return "outbound page";
        return resultMap;
    }

    // End Region Products
    // Region Cart

    @PutMapping("/cart/put")
    public String putCart(HttpServletRequest request, CartDTO cart) {
        HttpSession session = request.getSession();
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
        if (ssKey == null) return "auth error";

        cart.setMember_id(ssKey.getMember_id());
        memberService.addCart(cart);
        return "success";
    }


    @PostMapping("/cart/delete/{item_id}")
    public String deleteCart(HttpServletRequest request, CartDTO item) {
        HttpSession session = request.getSession();
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
        if (ssKey == null) return "auth error";

        item.setMember_id(ssKey.getMember_id());
        memberService.deleteCartByMemberAndItemId(ssKey.getMember_id(), item.getItem_id());
        return "success";
    }
    @PostMapping("/cart/delete")
    public String deleteSelectedCart(HttpServletRequest request,
                        @RequestParam("orderList") List<CartDTO> cartList) {
        HttpSession session = request.getSession();
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
        if (ssKey == null) return "auth error";

        int result = 1;
        for(CartDTO cart : cartList) {
            cart.setMember_id(ssKey.getMember_id());
//            result *= memberService.deleteCart(cart);
        }
        return "success";
    }


    //장바구니 시작
    @PostMapping("/updateCart")
    @ResponseBody
    public String updateCart(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody List<OrderDTO> orderList) {

        HttpSession session = request.getSession();
        OrderDTO order = (OrderDTO) session.getAttribute("cartList");

        System.out.println("---------"+orderList);

        if(order != null) {
//			memberService.addCart(orderList);
        }


        return "redirect:/";
    }

    @PostMapping("/deleteCart")
    public String deleteCart(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody List<OrderDTO> orderList) {
        HttpSession session = request.getSession();
        OrderDTO order = (OrderDTO) session.getAttribute("cartLists");

        System.out.println("---------"+orderList);

        if(order != null) {
//			memberService.deleteOrderStateByCart(orderList);
//			memberService.deleteOrdersByCart(orderList);
            return "success";
        }

        return "failed";
    }
    //장바구니 끝

    // End Region Cart
    // Region Order -----------------------------------------------------------------------------------------------

    @PostMapping("/order")
    public String setOrder(HttpSession session, @RequestBody OrderDTO order) {
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
        if (ssKey == null) return "auth error";
        String memberId = ssKey.getMember_id();

        OrderDTO orderData = memberService.addOrder(memberId, order.getItemList());
        return orderData.getOrder_id();
    }

    // End Region Order
}
