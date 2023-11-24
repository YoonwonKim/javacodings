package com.ecom.javacodings.common.transfer.table;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDTO {
	private String order_id;
	private String item_id;
	private String member_id;
	private int quantity;
	private int state;
	private Date reg_date;
	private String image;
	private String label;
	private int price;
}
