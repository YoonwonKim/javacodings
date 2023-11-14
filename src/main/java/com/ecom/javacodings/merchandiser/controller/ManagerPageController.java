package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class ManagerPageController {

	private static final Logger logger = LoggerFactory.getLogger(ManagerPageController.class);
	
    @Autowired
    ManagerService managerService;

    @RequestMapping("/products")
    public String products(HttpServletRequest request, HttpServletResponse response,
                           Model model) {
        PageDTO page = new PageDTO();
        page.setStart(0);
        page.setRow(15);
        page.setEnd(page.getRow() + page.getStart());
        model.addAttribute("itemList", managerService.listItem(page));
        model.addAttribute("categoryList", managerService.listCategory());
        model.addAttribute("tagList", managerService.listTags());

        return "/merchandiser/products";
    }

    @RequestMapping("/orders")
    public String orders(HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        PageDTO page = new PageDTO();
        page.setStart(0);
        page.setRow(15);
        page.setEnd(page.getRow() + page.getStart());

        model.addAttribute("orderList", managerService.listOrder(page));
        model.addAttribute("stateList", managerService.countOrderState());

        return "/merchandiser/orders";
    }
}