package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemDTO {
	private String item_id;
	private String category;
	private String reg_date;
	private String label;
	private String desc;
	private String price;
	private String stock;
	
	//ITEM_IMAGES
	private String path;
	
	//ITEM_TAGS
	private String tag;
	
	private int orders;
}
