package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartDTO extends OrderDTO {
    private String category    ;
    private String label       ;
    private String desc;
    private String path       ;
    private int price       ;
    private int stock       ;
    private int orders;
    private String member_id;
    private String item_id;
    private int quntity_id;
}
