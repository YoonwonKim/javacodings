package com.ecom.javacodings.common.transfer;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDTO {
	// Region Order

	protected String order_id;
	protected String item_id;
	protected String member_id;
	private String transaction_id;

	protected int quantity;
	protected Date reg_date;

	// End Region Order
	// Region Order state

	private int state;

	// End Region Order state
}
