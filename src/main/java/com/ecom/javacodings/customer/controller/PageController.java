package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.CartDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberAddressDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.service.IMemberService;

import com.ecom.javacodings.purchase.data.PurchaseData;
import com.ecom.javacodings.purchase.service.IPurchaseService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
	IMemberService memberService;

	@Autowired
	IPurchaseService payUpService;

    @RequestMapping()
    public String landing(HttpServletRequest request, Model model) {
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

    	return "customer/index";
    }

	@RequestMapping("/support")
	public String support() {
		return "customer/account/support";
	}


	// Region Account

	@RequestMapping("/account")
	public String information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("ssKey");
		
		if (member == null) return "redirect:/account/login";

		member = memberService.findMemberByIdAndPassword(
				member.getMember_id(), member.getPassword());
		member.setPassword(null); // Hide password for safe
		model.addAttribute("ssKey", member);

		MemberAddressDTO address = memberService.getPrimaryAddress(member.getMember_id());
		model.addAttribute("address", address);

		List<OrderDTO> countMemberOrders = memberService.countOrdersByMemberId(member.getMember_id());
		model.addAttribute("countMemberOrders", countMemberOrders);

		return "customer/account/information";
	}

	@RequestMapping("/account/login")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey != null) return "redirect:/";
		return "customer/account/login";
	}

	@RequestMapping("/account/register")
	public String register() {
		return "customer/account/register";
	}

	@RequestMapping("/account/find")
	public String findAccount() {
		return "customer/account/find";
	}

	@GetMapping("/account/{tab}")
	public String accountTab(@PathVariable("tab") String tab, HttpSession session, Model model) {
		String result = "customer/account/" + tab;
		MemberDTO member = (MemberDTO) session.getAttribute("ssKey");
		if (member == null) return "redirect:/account/login";
		String memberId = member.getMember_id();

		member = memberService.findMemberByIdAndPassword(
				member.getMember_id(), member.getPassword());
		member.setPassword(null); // Hide password for safe
		model.addAttribute("ssKey", member);

		MemberAddressDTO address = memberService.getPrimaryAddress(member.getMember_id());
		model.addAttribute("address", address);

		List<OrderDTO> countMemberOrders = memberService.countOrdersByMemberId(member.getMember_id());
		model.addAttribute("countMemberOrders", countMemberOrders);

		if (tab.equals("orders"))
		{
			List<OrderDTO> orderList = memberService.findAllByMemberOrderOrders(memberId);
			for (OrderDTO orderData : orderList) {
				 String orderId = orderData.getOrder_id();
				 List<CartDTO> itemList = memberService.findAllByMemberOrderItems(memberId, orderId);
				 orderData.setItemList(itemList);
			}
			model.addAttribute("orderList", orderList);
		}

		return result;
	}
		
	@RequestMapping("/account/orders/{order_id}")
	public String ordersDetail(@PathVariable("order_id") String orderId, Model model,
							   HttpSession session) {
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey == null) return "redirect:/";

	    OrderDTO orderData = memberService.findOrderItemsByOrderId(orderId);
	    model.addAttribute("order", orderData);
	    
	    List<CartDTO> itemList = memberService.findItemsByOrderId(orderId);
	    model.addAttribute("itemList", itemList);
	    	    
	    return "customer/account/ordersdetail";
	}
	// End Region Account
	// Region Product

	@GetMapping("/item/{item_id}")
	public String viewItem(Model model, @PathVariable("item_id") String itemId) {
		ItemDTO item = memberService.findItemByItemId(itemId);
		model.addAttribute("item", item);
		return "customer/item/view";
	}

	@RequestMapping("/item/c/{category}")
	public String listItem(Model model, @PathVariable("category") String category) {
		model.addAttribute("category", category.toUpperCase());
		return "customer/item/category";
	}
	

	// End Region Product
	// Region Event

	@RequestMapping("/event/list")
	public String eventList(Model model, String page, String row) {

		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int currentRow  = (row  == null) ? 0 : Integer.parseInt(row );

		if (currentRow != 0) memberService.setEventPageRow(currentRow);
		Map<String, Object> pageMap = memberService.getEventPageMap(currentPage);


		model.addAllAttributes(pageMap);
		System.out.println(pageMap.get("objectList"));

		return "customer/event/list";
	}

	@RequestMapping("/event/{event_id}")
	public String event(Model model, @PathVariable("event_id") String eventId) {
		model.addAttribute("mainBanner", memberService.getBannerImageByEventId(eventId));
		model.addAttribute("eventItem", memberService.getAllItemsByEventId(eventId));
		return "customer/event/view";
	}

	// End Region Event
	// Region Cart

	@RequestMapping("/cart")
	public String listCart(HttpServletRequest request,
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
		return "customer/order/cart";
	}

	// End Region Cart
	// Region Order

	@GetMapping("/order/purchase/{order_id}")
	public String purchaseOrder(@PathVariable("order_id") String orderId,
								Model model, HttpSession session) {
		List<CartDTO> cartList = memberService.findAllCartByOrderId(orderId);
		OrderDTO orderData = memberService.findOrderByOrderId(orderId);
		orderData.setItemList(cartList);

		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey == null) return "redirect:/";
		String memberId = ssKey.getMember_id();
		memberService.removeCartByOrderId(memberId, orderId);

		Map<String, String> responseBody = payUpService.request(orderData);
		model.addAllAttributes(responseBody);
		model.addAttribute("order_id", orderId);
		List<ItemDTO> itemList = memberService.findAllItemsByOrderId(orderId);
		model.addAttribute("amount", orderData.getAmount());
		model.addAttribute("itemList", itemList);
		return "customer/order/purchase";
	}

	@PostMapping("/order/confirm/{order_id}")
	public String confirmOrder(HttpSession session,
							   String ordr_idxx, @PathVariable("order_id") String orderId,
							   PurchaseData purchaseData, Model responseBody) {
		MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");
		if (ssKey == null) return "redirect:/";
		String page = "redirect:/account/orders";
		if (purchaseData.getRes_cd() == null || !purchaseData.getRes_cd().equals("0000")) return page;

		// * 결제 시도 --------------------------------

		Map<String, String> purchaseResponse = payUpService.purchase(ordr_idxx, purchaseData);
		String responseCode = purchaseResponse.get("responseCode");

		// * 예외 처리 --------------------------------

		Boolean isSuccess = responseCode.equals("0000");
		Boolean isCheck = responseCode.equals("CCC0");
		if(isCheck   == true) {
			page = purchaseOrder(orderId, responseBody, session);
			responseBody.addAttribute("isCheck", true);
			return page;
		}
		if(isSuccess == false) return page;

		// * 결제 정보 저장 --------------------------------

		String transactionId = purchaseResponse.get("transactionId");
		String regDate = purchaseResponse.get("authDateTime");
		memberService.successPurchase(orderId, regDate);
		memberService.setTransactionIdByOrderId(transactionId, orderId);

		// * 페이지 구성 및 반환 --------------------------------

		responseBody.addAttribute("isCheck", false);
		responseBody.addAllAttributes(purchaseResponse);
		return page;
	}

	// End Region Order
}

