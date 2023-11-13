package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/actions")
public class ManagerActionController {
    // Region Services
    @Autowired ManagerService managerService;
    // End Region Services
    // Region Get Data
    @GetMapping("/get_item")
    public String getItem(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        String item_id = request.getParameter("item_id");
        ItemDTO item = managerService.getItemById(item_id);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        return result;
    }
    @GetMapping("/get_tags")
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
    @PutMapping("/set_item")
    public String setItem(HttpServletRequest request, HttpServletResponse response,
                          ItemDTO item, @RequestParam("tags") List<String> tags) {
        int result = 0;
        result += managerService.updateItem(item);
        result *= managerService.updateTags(item.getItem_id(), tags);
        if (result > 0) return "success";
        return "error";
    }
    // End Region Set Data
}
