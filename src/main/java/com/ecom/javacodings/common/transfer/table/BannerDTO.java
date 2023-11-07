package com.ecom.javacodings.common.transfer.table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BannerDTO {
	  private String banner_id;
	  private String item_id;
      private String category;
	  private String label;
	  private String desc;
	  private String image;
}
