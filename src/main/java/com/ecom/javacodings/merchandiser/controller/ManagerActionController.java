package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.ItemImageDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.common.transfer.EventDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/actions")
public class ManagerActionController {

    @Autowired ManagerService managerService;

    // Region Get Item

    @GetMapping("/item/get")
    public Map<String, Object> getItem(String itemId) {
        ItemDTO item  = managerService.readItemById(itemId);
        String[] tags = managerService.findTagsByItemId(itemId);

        Map<String, Object> resultMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        resultMap.putAll(mapper.convertValue(item, Map.class));
        resultMap.put("tags", tags);
        return resultMap;
    }

    @GetMapping("/item/get/images")
    public List<ItemImageDTO> getImages(String itemId) {
        List<ItemImageDTO> imageList = managerService.findImagesByItemId(itemId);
        ObjectMapper mapper = new ObjectMapper();
        return imageList;
    }

    @PutMapping("/item/put")
    public String putItem(ItemDTO item,
                          @RequestParam(required=false, name="tags[]") List<String> tags) {
        String result;
        Boolean isExistItem = item.getItem_id().length() > 0;
        if (isExistItem) {result = managerService.editItem(item, tags); }
        else { result = managerService.createItem(item, tags); }
        return result;
    }

    @PutMapping("/item/put/images")
    public String putImages(String imageList, String itemId,
                           @RequestParam(value = "fileList", required = false) List<MultipartFile> fileList)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Object> itemImageList = mapper.readValue(imageList, List.class);

        try {
            Map<String, String> imageNameList = managerService.uploadImages(itemId, fileList);
            for(int i = 0; i < imageNameList.size(); i++) {
                String originalName = (String) imageNameList.keySet().toArray()[i];
                String newName = (String) imageNameList.get(originalName);

                for(int j = 0; j < itemImageList.size(); j++) {
                    Map<String, String> imageObject = (Map<String, String>) itemImageList.get(j);
                    if (imageObject.get("path").equals(originalName))
                        imageObject.put("path", newName);
                }
            }
        } catch (Exception e) {}

        managerService.setItemImages(itemId, itemImageList);
        return "success";
    }

    @GetMapping("/item/tag/list")
    public List<String> getTags(String item_id) {
        return managerService.findAllTags();
    }

    // End Region Get Data

    @PutMapping("/update_order")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response,
                              OrderDTO order) {
        int result = managerService.updateOrderStates(order);
    	return "success";
    }
    
    @PostMapping("/eventAdd")
    public String eventAdd(HttpServletRequest request,
							HttpServletResponse response,
							Model model,
							EventDTO eventDTO) {
    	
    	System.out.println("asdf");
    	managerService.eventAdd(eventDTO);
    	
    	
    	return "redirect:/admin/eventsPg";
    }
    
    @PostMapping("/eventMgt")
    @ResponseBody
    public String eventMgt(HttpServletRequest request,
                           HttpServletResponse response,
                           EventDTO eventDTO) {
        
        // eventDTO를 활용하여 필요한 처리 로직을 구현합니다.
        managerService.stateUpdate(eventDTO);
        
        System.out.println(eventDTO);
        
        return "redirect:/admin/eventsPg";
    }
}
