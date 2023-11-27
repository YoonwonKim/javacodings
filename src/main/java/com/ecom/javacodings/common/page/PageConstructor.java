package com.ecom.javacodings.common.page;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PageConstructor {
    public Map<String, Object> getPages(PageInterface listMethod, String page, String row, int count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // Set Page
        if (page == null) page = "1";
        if (row  == null) row  = "15";
        PageDTO pageObject = new PageDTO(Integer.parseInt(page), Integer.parseInt(row));

        List<Object> objectList = (List<Object>) listMethod.listObjects(pageObject).get(0);
        resultMap.put("objectList", objectList);

        resultMap.put("currentPage",  pageObject.getPage());
        resultMap.put("row",   pageObject.getRow());
        resultMap.put("start", pageObject.getStart());
        resultMap.put("end",   pageObject.getEnd());
        resultMap.put("totalItems", count);

        double countPage = (double) count / pageObject.getRow();
        int extra = (((countPage - (int)countPage) > 0)?1:0);
        int totalPages = (int)countPage + extra;
        resultMap.put("totalPages", totalPages);

        return resultMap;
    }
}
