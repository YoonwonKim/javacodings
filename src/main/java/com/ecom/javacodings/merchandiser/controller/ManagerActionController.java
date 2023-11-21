package com.ecom.javacodings.merchandiser.controller;

import com.ecom.javacodings.common.transfer.table.ItemDTO;
import com.ecom.javacodings.common.transfer.table.OrderDTO;
import com.ecom.javacodings.common.transfer.table.TagDTO;
import com.ecom.javacodings.merchandiser.service.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/admin/actions")
public class ManagerActionController {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(ManagerActionController.class);
	
    @Autowired
    ManagerService managerService;

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    // Region Get Data
    @GetMapping("/get_item")
    @ResponseBody
    public String getItem(HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        String item_id = request.getParameter("item_id");
        ItemDTO item = managerService.readItemById(item_id);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(item);
        return result;
    }
    @GetMapping("/get_tags")
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
    @PutMapping("/set_item")
    public String setItem(HttpServletRequest request, HttpServletResponse response,
                          ItemDTO item, @RequestParam(required=false, name="tags") List<String> tags)
            throws JsonProcessingException {
        int result = 0;
        result += managerService.updateItem(item);
        result *= managerService.updateTags(item.getItem_id(), tags);
        if (result > 0) return "success";
        return "error";
    }
    @PutMapping("/set_image")
    public String setImage(HttpServletRequest request, HttpServletResponse response,
                           ItemDTO item, @RequestParam("file") MultipartFile file) {
        // 이미지 파일 이름을 20자리 랜덤 문자열로 지정
        int leftLimit  =  48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        String randomImageName = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // 파일 저장 및 데이터베이스 업데이트
        String absoluteClassPath = new File("").getAbsolutePath() + "/src/main/webapp/";
        File targetFile = new File(absoluteClassPath + filePath + "/" + randomImageName + ".png");
        targetFile.getParentFile().mkdirs();
        try {
            file.transferTo(targetFile);
            item.setImage(randomImageName);
            int r = managerService.updateImageById(item);
        }
        catch (IOException e) { return "error, IOException"; };
        return "error";
    }
    @PostMapping("/item/create")
    public String setItem(HttpServletRequest request, HttpServletResponse response,
                          ItemDTO item,
                          @RequestParam(required=false, name="tags") List<String> tags,
                          @RequestParam("file") MultipartFile file) {
        int result = 0;

        // 이미지 파일 이름을 20자리 랜덤 문자열로 지정
        int leftLimit  =  48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        String randomImageName = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        // 상품 아이디를 30자리 랜덤 문자열로 지정
        targetStringLength = 30;
        String  item_id = "";
        ItemDTO checkDuplicate = new ItemDTO();
        do {
            item_id = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            checkDuplicate = managerService.readItemById(item_id);
        } while (checkDuplicate != null);
        item.setItem_id(item_id);

        // 파일 저장 및 데이터베이스 업데이트
        String absoluteClassPath = new File("").getAbsolutePath() + "/src/main/webapp/";
        File targetFile = new File(absoluteClassPath + filePath + "/" + randomImageName + ".png");
        targetFile.getParentFile().mkdirs();
        try {
            file.transferTo(targetFile);
            item.setImage(randomImageName);

            // 상품 정보 업데이트
            result += managerService.createItem(item);
            result *= managerService.updateTags(item.getItem_id(), tags);
        }
        catch (IOException e) { return "error, IOException"; };
        if (result > 0) return "success";
        return "error";
    }
    // End Region Set Data
    
    //orderUpdate 구동되지 않음
    @PutMapping("/update_order")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response,
                              OrderDTO order) {
        int result = managerService.updateOrderStates(order);
    	return "success";
    }
}
