package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.ItemImageDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/actions")
public class ManagerActionController {
    // Region Services
    @Autowired ManagerService managerService;
    // End Region Services
    // Region Get Item

    @PutMapping("/item/add")
    public String setItem(ItemDTO item,
                          @RequestParam(required=false, name="tags[]") List<String> tags) {
        String result;
        result = managerService.createItem(item);
        if (!result.equals("error")) managerService.updateTags(item.getItem_id(), tags);
        return result;
    }

    @GetMapping("/item/get")
    public Map<String, Object> getItem(String itemId) {
        ItemDTO item  = managerService.readItemById(itemId);
        String[] tags = managerService.findTagsByItemId(itemId);
        List<ItemImageDTO> images = managerService.findImagesByItemId(itemId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("item", item);
        resultMap.put("tags", tags);
        resultMap.put("images", images);
        return resultMap;
    }

    @PutMapping("/item/edit")
    public String editItem(ItemDTO item, @RequestParam(required=false, name="tags[]") List<String> tags) {
        int result = 0;
        result += managerService.updateItem(item);
        if (tags != null) result *= managerService.updateTags(item.getItem_id(), tags);

        if (result > 0) return "success";
        return "error";
    }

    @GetMapping("/item/tag/list")
    public List<String> getTags(String item_id) {
        return managerService.findAllTags();
    }

    @PutMapping("/item/image/add")
    public String setImage(String itemId, @RequestParam("file") MultipartFile file) {
        int result = managerService.updateImage(itemId, file);

        if (result > 0) return "success";
        return "error";
    }

    // End Region Get Data

    @PutMapping("/update_order")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response,
                              OrderDTO order) {
        int result = managerService.updateOrderStates(order);
    	return "success";
    }
}
