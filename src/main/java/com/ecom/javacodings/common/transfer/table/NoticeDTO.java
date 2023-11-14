package com.ecom.javacodings.common.transfer.table;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NoticeDTO {
	private int rr;
	private String notice_id;
	private Date red_date;
	private int label;
	private String content;
	private String state;
}
