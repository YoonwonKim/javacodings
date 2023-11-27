package com.ecom.javacodings.common.transfer;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDTO {
	String order_id;
	String item_id;
	String member_id;
	int quantity;
	int state;
	Date reg_date;
}
