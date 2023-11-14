package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageDTO {
    private int start;
    private int row;
    private int end;
    private int member_id;
}
