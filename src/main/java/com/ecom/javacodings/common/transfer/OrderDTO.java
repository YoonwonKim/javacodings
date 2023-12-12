package com.ecom.javacodings.common.transfer;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDTO {

	protected String order_id;
	protected String item_id;
	protected String member_id;
	private String transaction_id;
	protected int amount;

	protected String reg_date;
	private int state;

}
