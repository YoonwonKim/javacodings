package com.ecom.javacodings.common.transfer.table;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NoticeDTO {
	private int rr;
	private String notice_id;
	private Date reg_date;
	private String label;
	private String content;
	private String author_id;
	private String state;
}
