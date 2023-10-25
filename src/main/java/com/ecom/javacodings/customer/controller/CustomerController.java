package com.ecom.javacodings.customer.controller;

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
    CustomerService customerService;

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

        String member_id =  request.getParameter("member_id");
        String password  =  request.getParameter("password");
        int result_value = customerService.login(member_id, password);
        if (result_value == 1) result = "success";

        return result;
    }
}
