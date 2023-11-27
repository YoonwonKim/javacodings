package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartDTO extends OrderDTO {
    private String category    ;
    private String label       ;
    private String desc;
    private String image       ;
    private int price       ;
    private int stock       ;
    private int orders;
}
