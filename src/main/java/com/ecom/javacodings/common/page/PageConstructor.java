package com.ecom.javacodings.common.page;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PageConstructor {

    private IListMethod  listMethod;
    private ICountMethod countMethod;
    private String criteria;
    private int row;

    // * Constructor
    public PageConstructor(int row, IListMethod listMethod, ICountMethod countMethod) {
        this.listMethod  = listMethod;
        this.countMethod = countMethod;
        this.row = row;
    }


    public void setRow(int row) {
        this.row = row;
    }
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * 데이터베이스에서 현재 페이지에 해당하는 데이터와 페이지에 대한 정보를 맵에 담아 반환한다
     * @param page
     * @return
     */
    public Map<String, Object> getPageMapOrNull(int page) {

        ObjectMapper mapper = new ObjectMapper();

        // Page Metadata
        PageDTO pageData = new PageDTO(page, row);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.putAll(mapper.convertValue(pageData, Map.class));

        int count = countMethod.count(criteria);
        pageMap.put("totalItems", count);
        double countPage = (double) count / pageData.getRow();
        int leaves = (((countPage - (int)countPage) > 0)?1:0);
        int totalPages = (int)countPage + leaves;
        pageMap.put("totalPages", totalPages);
        if(page > totalPages) return null;

        // Get Page
        List<Object> objectList = (List<Object>) listMethod.getList(criteria, pageData).get(0);
        pageMap.put("objectList", objectList);
        pageMap.put("criteria", criteria);

        return pageMap;
    }
}
