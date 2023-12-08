package com.ecom.javacodings.common.transfer.table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemDTO {
                               
	private String item_id     ;
	private String category    ;
	private String reg_date    ;
	private String label       ;
	private String desc;
	private int price       ;
	private int stock       ;
	private String path ; 
}
