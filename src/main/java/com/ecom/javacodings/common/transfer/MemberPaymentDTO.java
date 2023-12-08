package com.ecom.javacodings.common.transfer;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Data
@Scope( value= WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class MemberPaymentDTO {
    String member_id;
    String card_no;
    String card_pw;
    String expire_year;
    String expire_month;
}
