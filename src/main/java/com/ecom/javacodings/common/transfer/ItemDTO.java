package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemDTO {

	protected String item_id;
	private String category;
	private String reg_date;

	protected String label;
	protected String desc;
	protected String path;

	protected int price;
	protected int stock;

}
