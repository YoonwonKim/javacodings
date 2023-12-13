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
public class MemberDTO {
    String member_id;
    String password;

    String reg_date;
    String role;
    String level;

    String name;
    String email;
    String birth;
    String phone;
}
