package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.page.PageDTO;
import com.ecom.javacodings.common.transfer.EventDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ManagerPageController {

    @Autowired
    ManagerService  managerService;

    // Region Pages

    @RequestMapping()
    public String landing(HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        return "/merchandiser/index";
    }

    @RequestMapping("/item")
    public String products(Model model, String page, String row) {
        // * List Items
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int currentRow  = (row  == null) ? 0 : Integer.parseInt(row );

        if (currentRow != 0) managerService.setProductPageRow(currentRow);
        Map<String, Object> pageMap = managerService.getProductPageMap(currentPage);

        model.addAllAttributes(pageMap);
        model.addAttribute("categoryList", managerService.listCategory());
        model.addAttribute("tagList", managerService.findAllTags());

        // * Summary Items
        List<Map<String, Object>> summaryList = new ArrayList<>();
        Map<String, Object> summary = new HashMap<>();
        summary.put("label", "상품 분류 요약");
        summary.put("itemList", managerService.summaryItemsByCategory());
        summaryList.add(summary);

        summary = new HashMap<>();
        summary.put("label", "상품 태그 요약");
        summary.put("itemList", managerService.summaryItemsByTag());
        summaryList.add(summary);
        model.addAttribute("summaryList", summaryList);

        // * Return Page
        if (pageMap.get("responseMsg") != null)
            return "redirect:/admin/item?page=" + pageMap.get("totalPages") + "&row=" + pageMap.get("row");
        return "merchandiser/item/main";
    }

    @RequestMapping("/order")
    public String orders(Model model, String page, String row) {
        Map<String, Object> summary = managerService.summaryOrderState();
        model.addAttribute("summary", summary);

        int currentRow  = (row  == null)?0:Integer.parseInt(row);
        if (currentRow != 0) managerService.setOrderPageRow(currentRow);
        int currentPage = (page == null)?1:Integer.parseInt(page);
        Map<String, Object> pageMap = managerService.getOrderPageMap(currentPage);
        model.addAllAttributes(pageMap);
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

    @RequestMapping("/event/{event_id}")
    public String events(Model model, @PathVariable("event_id") String eventId) {

        PageDTO page = new PageDTO(1, 15);
        model.addAttribute("itemList", managerService.listEventItem(page, eventId));
//        model.addAttribute("event", managerService.listEvent(page));


        model.addAttribute("eventId", eventId);
        return "merchandiser/event/view";
    }
}
