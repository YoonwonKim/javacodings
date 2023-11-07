package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class MangerPageController {
    // Region Services
    @Autowired ManagerService managerService;
    // End Region Services
    // Region Pages
    @RequestMapping("/products")
    public String products(HttpServletRequest request, HttpServletResponse response,
                           Model model) {
        //? Set Page
        PageDTO page = new PageDTO();
        page.setStart(0);
        page.setRow(15);
        page.setEnd(page.getRow() + page.getStart());

        //? Return data
        model.addAttribute("itemList", managerService.listItem(page));
        model.addAttribute("categoryList", managerService.listCategory());
        model.addAttribute("tagList", managerService.listTags());

        return "/merchandiser/products";
    }
    // End Region Pages
}
