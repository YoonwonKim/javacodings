package com.ecom.javacodings.customer.controller;

import com.ecom.javacodings.common.PageConstructor;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.table.MemberDTO;
import com.ecom.javacodings.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actions")
public class ActionController {

    PageConstructor pageConstructor = new PageConstructor();

    @Autowired
    CustomerService memberService;

    /**
     * RQ-001 로그인 기능 구현<br>
     * 비동기 통신 후 문자열 형태로 결과를 반환한다.
     * 성공 시 세션에 값을 저장한다.
     */
    @PostMapping("/account/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String result = "failed";
        HttpSession session = request.getSession();
        MemberDTO ssKey = new MemberDTO();

        ssKey.setMember_id(request.getParameter("member_id"));
        ssKey.setPassword(request.getParameter("password"));
        ssKey = memberService.login(ssKey);
        if (ssKey == null) {
            result = "failed";
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
    @PostMapping("/account/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String result = "failed";
        HttpSession session = request.getSession();
        MemberDTO ssKey = (MemberDTO) session.getAttribute("ssKey");

        if (ssKey == null) {
            result = "success, but already logged out";
        } else {
            session.removeAttribute("ssKey");
            result = "success";
        }

        return result;
    }

    @PutMapping("/account/register")
    public String register(HttpServletRequest request, HttpServletResponse response,
                           MemberDTO member) {
        member.setEmail(member.getEmail() + request.getParameter("email-domain"));
        memberService.memberJoin(member);

        response.setContentType("application/json");
        return "success";
    }

    
    @GetMapping("/account/search/id")
    public String searchMember(HttpServletRequest request, HttpServletResponse response,
    							MemberDTO member) {
    	String member_id = memberService.searchId(member);
        if (member_id == null) return "error";
    	return member_id;
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
        memberService.temporaryPassword(member);
        return randomPassword;
    }
    

    @PostMapping("/account/duplicate")
    public String checkDuplicate(HttpServletRequest request, HttpServletResponse response,
                                 MemberDTO member) {
        System.out.println(member.getMember_id());
        int r = memberService.idCheck(member.getMember_id());
        if (r > 0) return "duplicated";
        return "not-duplicated";
    }


    // Region Products
    @PostMapping("/product/list/{category}")
    public Object getProducts(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable("category") String category) {
        Map<String, Object> resultMap = pageConstructor.getPages(
                (PageDTO pageSet) -> Collections.singletonList(memberService.listProductsInCategory(pageSet, category.toUpperCase())),
                request.getParameter("page"),
                request.getParameter("row"),
                memberService.countProductsInCategory(category.toUpperCase())
        );

        if((Integer) resultMap.get("currentPage") > (Integer) resultMap.get("totalPages")) {
            return "outbound page";
        }

        return resultMap;
    }
    // End Region Products
}
