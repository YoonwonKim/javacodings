package com.ecom.javacodings.common.page;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageDTO {
    private int currentPage;
    private int row;
    private int start, end;

    public PageDTO(int page, int row) {
        this.currentPage = page;
        this.row  = row;

        this.start = (page - 1) * row;
        this.end   = start + row;
    }
}
