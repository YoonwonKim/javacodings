package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OrderDTO {

	private String order_id;
	private String member_id;
	private String transaction_id;
	private int amount;

	private String reg_date;
	private int state;

	private List<CartDTO> itemList;

}
