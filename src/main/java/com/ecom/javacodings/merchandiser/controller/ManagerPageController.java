package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.table.EventDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ManagerPageController {
    // Region Pages
    @RequestMapping()
    public String landing(HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        return "/merchandiser/index";
    }

    @Autowired
    ManagerService  managerService;

    @RequestMapping("/item")
    public String products(Model model, String page, String row) {
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int currentRow  = (row  == null) ? 0 : Integer.parseInt(row );

        if (currentRow != 0) managerService.setProductPageRow(currentRow);
        Map<String, Object> pageMap = managerService.getProductPageMap(currentPage);

        model.addAllAttributes(pageMap);
        model.addAttribute("categoryList", managerService.listCategory());
        model.addAttribute("tagList", managerService.findAllTags());

        if (pageMap.get("responseMsg") != null)
            return "redirect:/admin/item?page=" + pageMap.get("totalPages") + "&row=" + pageMap.get("row");
        return "merchandiser/item/list";
    }

    @RequestMapping("/orders")
    public String orders(HttpServletRequest request, HttpServletResponse response,
                          Model model) {
//        Map<String, Object> pageMap = pageConstructor.getPages(
//                (PageDTO pageSet) -> Collections.singletonList(managerService.listOrder(pageSet)),
//                request.getParameter("page"),
//                request.getParameter("row"),
//                managerService.countOrders()
//        );
//
//        if((Integer) pageMap.get("currentPage") > (Integer) pageMap.get("totalPages")) {
//            return "redirect:/admin/orders?page=" + pageMap.get("totalPages") + "&row=" + pageMap.get("row");
//        }
//
//        model.addAllAttributes(pageMap);
        model.addAttribute("stateList", managerService.countOrderState());
        return "/merchandiser/orders";
    }
    // End Region Pages

    @RequestMapping("/event")
    public String event(Model model, String page, String row) {
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int currentRow  = (row  == null) ? 0 : Integer.parseInt(row );

        if (currentRow != 0) managerService.setEventPageRow(currentRow);
        Map<String, Object> pageMap = managerService.getEventPageMap(currentPage);

        List<String> itemList = new ArrayList<>();
        for(EventDTO event : (List<EventDTO>) pageMap.get("objectList")) {
            itemList.add(managerService.getItemsByEventId(event.getEvent_id()));
        }

        model.addAllAttributes(pageMap);
        model.addAttribute("itemList", itemList.toArray());
        return "merchandiser/event/list";
    }

    @RequestMapping("/event/item")
    public String events(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model,
                         EventDTO eventDTO) {

        PageDTO page = new PageDTO(1, 15);

        model.addAttribute("itemList", managerService.listEventItem(page));
        model.addAttribute("event", managerService.listEvent(page));

        return "merchandiser/event/item";
    }
}
