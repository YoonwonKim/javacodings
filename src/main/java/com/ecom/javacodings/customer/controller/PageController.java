package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @RequestMapping("/join")
    public String join(HttpServletRequest request, HttpServletResponse response,
    					MemberDTO mdto, Model model) {
    	
    	return "customer/fragments/join";
    }
    
    @RequestMapping("/registerProc")
    public String registerProc(HttpServletRequest request,
    							HttpServletResponse response,
    							MemberDTO mdto, 
    							Model model) throws ParseException {
    	String phone = request.getParameter("phone1") + request.getParameter("phone2");
    	String email = request.getParameter("email") + "@" + request.getParameter("email2");
    	String birth = request.getParameter("birth1")  + "-" + request.getParameter("birth2")
    	+ "-" + request.getParameter("birth3");
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date to = formatter.parse(birth);
    	
    	mdto.setPhone(Integer.parseInt(phone));
    	mdto.setEmail(email);
    	mdto.setBirth(to);
    	
    	System.out.println(to);
    	try {
			int r = memberService.memberJoin(mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
    @RequestMapping("/idCheck")
	@ResponseBody
	public int idCheck(HttpServletRequest request, 
						HttpServletResponse response,
						Model model,
						MemberDTO mdto) {
		int cnt = 0;	
		  if(mdto.getMember_id()!=null) {
			  cnt = memberService.idCheck(mdto.getMember_id());
		  }
		return cnt;
	}
    
    @RequestMapping("/loginpage")
    public String login(HttpServletRequest request, HttpServletResponse response,
            Model model) {
    	
    	return "customer/login";
    }        
    
	@RequestMapping("/searchMember")
	public String searchMember(HttpServletRequest request, HttpServletResponse response) {
	  	
		return "customer/searchmember";
	}
	@RequestMapping("/noticelist")
	public String noticelist(HttpServletRequest request, HttpServletResponse response, Model model) {
		PageDTO page = new PageDTO();
        page.setStart(0);
        page.setRow(15);
        page.setEnd(page.getRow() + page.getStart());

		model.addAttribute("notice", memberService.NoticeList(page));
		return "customer/notice";
	}
}

