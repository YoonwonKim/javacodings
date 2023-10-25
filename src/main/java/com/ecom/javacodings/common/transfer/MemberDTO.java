package com.ecom.javacodings.common.transfer;

import java.sql.Date;

public class MemberDTO {
    String member_id;
    String password;
    String role;

    // Region Metadata
    Date reg_date;
    String name;
    String email;
    int phone;
    Date birth;
    String zipcode;
    String address, address2;
    // End Region Metadata
}