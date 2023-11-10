package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.common.transfer.PageDTO;
import com.ecom.javacodings.common.transfer.TagDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/actions")
public class ActionManagerController {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(ActionManagerController.class);
	
    @Autowired
    ManagerService managerService;

    // Region Get Data
    @PostMapping("/get_item")
    @ResponseBody
    public String getItem(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        String item_id = request.getParameter("item_id");
        ItemDTO item = managerService.getItemById(item_id);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        return result;
    }
    @PostMapping("/get_tags")
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
    @PostMapping("/set_item")
    @ResponseBody
    public String setItem(HttpServletRequest request, HttpServletResponse response,
                          ItemDTO item, @RequestParam(required=false, name="tags") List<String> tags)
            throws JsonProcessingException {
        int result = 0;
        result += managerService.updateItem(item);
        result *= managerService.updateTags(item.getItem_id(), tags);
        if (result > 0) return "success";
        return "error";
    }
    // End Region Set Data
    
    //orderUpdate 구동되지 않음
    @PostMapping("/update_order")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response,
                              Model model) {

        //HttpSession session = request.getSession();
        logger.info("attempt to update order: "+request.getParameter("data"));
//        List<OrderDTO> orderList = new ObjectMapper().readValue(orders.toString(), List.class);


//    	model.addAttribute("orderUpdate", order);
    	return "success";
    }

}