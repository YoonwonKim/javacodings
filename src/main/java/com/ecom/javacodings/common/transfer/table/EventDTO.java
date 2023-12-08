package com.ecom.javacodings.common.transfer.table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventDTO {

	private String event_id  ;
	private String label;
	private String category  ;
	private String content   ;
	private String start_date ;
	private String end_date ;
}
