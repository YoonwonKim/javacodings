package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventBannerDTO {
	
	private String event_id;
    private String label;
    private String image;
    private String category;
}
