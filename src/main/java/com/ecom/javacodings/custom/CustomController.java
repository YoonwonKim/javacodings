package com.ecom.javacodings.custom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CustomController {
    @RequestMapping()
    public String main(HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        return "index";
    }
}
