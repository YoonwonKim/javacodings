package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartDTO extends ItemDTO {

    private String member_id;
    private String order_id;
    private int amount;

}
