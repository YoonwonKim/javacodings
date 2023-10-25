package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        return "index";
    }

    /**
     * RQ-001 로그인 기능 구현<br>
     * 비동기 통신 후 문자열 형태로 결과를 반환한다.
     */
    @PostMapping("/action/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String result = "failed";
        MemberDTO member = new MemberDTO();

        member.setMember_id(request.getParameter("member_id"));
        member.setPassword(request.getParameter("password"));
        int result_value = memberService.login(member);
        if (result_value == 1) result = "success";

        return result;
    }
}
