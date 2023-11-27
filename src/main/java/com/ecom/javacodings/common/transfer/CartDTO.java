package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartDTO extends OrderDTO {
    private String image;
    private String label;
    private String desc;
    private int stock;
    private int price;
}
