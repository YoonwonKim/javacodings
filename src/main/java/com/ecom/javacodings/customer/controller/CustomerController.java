package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.service.CustomerService;
import com.ecom.javacodings.customer.transfer.BannerDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
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
public class CustomerController {
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

    /**
     * RQ-001 로그인 기능 구현<br>
     * 비동기 통신 후 문자열 형태로 결과를 반환한다.
     * 성공 시 세션에 값을 저장한다.
     */
    @PostMapping("/action/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String result = "failed";
        HttpSession session = request.getSession();
        MemberDTO ssKey = new MemberDTO();

        ssKey.setMember_id(request.getParameter("member_id"));
        ssKey.setPassword(request.getParameter("password"));
        ssKey = memberService.login(ssKey);
        if (ssKey == null) {
            result = "failed, ssKey value is null;";
        } else {
            result = "success";
            session.setAttribute("ssKey", ssKey);
        }

        return result;
    }

    /**
     * RQ-002 로그아웃 기능 구현
     * 비동기 통신 후 결과를 반환하고 세션을 초기화한다.
     */
    @PostMapping("/action/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String result = "failed";
        HttpSession session = request.getSession();
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");

        if (ssKey != null) {
            result = "success, but already logged out";
        } else {
            session.removeAttribute("ssKey");
            result = "success";
        }

        return result;
    }
}
