package com.ecom.javacodings.common.page;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PageConstructor {
    public Map<String, Object> getPages(PageInterface listMethod, String criteria, String page, String row, int count) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("criteria", criteria);

        // Set Page data
        if (page == null) page = "1";
        if (row  == null) row  = "15";
        PageDTO pageObject = new PageDTO(Integer.parseInt(page), Integer.parseInt(row));

        pageMap.put("currentPage",  pageObject.getPage());
        pageMap.put("row",   pageObject.getRow());
        pageMap.put("start", pageObject.getStart());
        pageMap.put("end",   pageObject.getEnd());
        pageMap.put("totalItems", count);

        // Get objectList
        List<Object> objectList = (List<Object>) listMethod.listObjects(pageMap).get(0);
        pageMap.put("objectList", objectList);

        // Pagination
        double countPage = (double) count / pageObject.getRow();
        int extra = (((countPage - (int)countPage) > 0)?1:0);
        int totalPages = (int)countPage + extra;
        pageMap.put("totalPages", totalPages);

        if(Integer.parseInt(page) > totalPages) return null;
        return pageMap;
    }
    public Map<String, Object> getPages(PageInterface listMethod, String page, String row, int count) {
        return getPages(listMethod, null, page, row, count);
    }
}
