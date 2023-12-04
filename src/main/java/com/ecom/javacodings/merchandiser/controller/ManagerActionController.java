package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.common.transfer.TagDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/actions")
public class ManagerActionController {
    // Region Services
    @Autowired ManagerService managerService;
    // End Region Services
    // Region Get Data
    @GetMapping("/item/read")
    @ResponseBody
    public String getItem(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        String item_id = request.getParameter("item_id");
        ItemDTO item = managerService.readItemById(item_id);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        return result;
    }
    @GetMapping("/item/tags")
    @ResponseBody
    public String getTags(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        String item_id = request.getParameter("item_id");
        List<TagDTO> tags = managerService.listTagsById(item_id);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(tags);
        return result;
    }
    // End Region Get Data
    // Region Set Data
    @PutMapping("/item/update")
    public String setItem(HttpServletRequest request, HttpServletResponse response,
                          ItemDTO item, @RequestParam(required=false, name="tags") List<String> tags) {
        int result = 0;
        result += managerService.updateItem(item);
        result *= managerService.updateTags(item.getItem_id(), tags);

        if (result > 0) return "success";
        return "error";
    }
    @PutMapping("/item/image")
    public String setImage(HttpServletRequest request, HttpServletResponse response,
                           ItemDTO item, @RequestParam("file") MultipartFile file) {
        int result = managerService.updateImageById(item, file);

        if (result > 0) return "success";
        return "error";
    }
    @PostMapping("/item/create")
    public String setItem(HttpServletRequest request, HttpServletResponse response,
                          ItemDTO item,
                          @RequestParam(required=false, name="tags") List<String> tags,
                          @RequestParam("file") MultipartFile file) {
        int result = 0;
        result += managerService.updateImageById(item, file);
        result *= managerService.createItem(item);
        result *= managerService.updateTags(item.getItem_id(), tags);

        if (result > 0) return "success";
        return "error";
    }
    // End Region Set Data
    
    @PutMapping("/update_order")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response,
                              OrderDTO order) {
        int result = managerService.updateOrderStates(order);
    	return "success";
    }
}
