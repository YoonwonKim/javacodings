package com.ecom.javacodings.customer.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemDTO {
                               
	private String item_id     ;
	private String category    ;
	private String reg_date    ;
	private String label       ;
	private String description ;
	private String image       ;
	private int price       ;
	private int stock       ;
}
