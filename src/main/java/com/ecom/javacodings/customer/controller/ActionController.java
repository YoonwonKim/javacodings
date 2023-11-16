package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
public class ActionController {
    @Autowired
    CustomerService memberService;

    /**
     * RQ-001 로그인 기능 구현<br>
     * 비동기 통신 후 문자열 형태로 결과를 반환한다.
     * 성공 시 세션에 값을 저장한다.
     */
    @PostMapping("/login")
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
    
    @PostMapping("/logout")
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
