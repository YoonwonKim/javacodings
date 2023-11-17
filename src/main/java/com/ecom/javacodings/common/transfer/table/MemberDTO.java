package com.ecom.javacodings.common.transfer.table;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@Data
@Scope( value= WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class MemberDTO {
    String member_id;
    String password;
    String role;

    // Region Metadata
    Date reg_date;
    String name;
    String email;
    String phone;
    String birth;
    String zipcode;
    String address, address2;
    // End Region Metadata
}