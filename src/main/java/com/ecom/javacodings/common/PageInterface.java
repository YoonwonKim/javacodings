package com.ecom.javacodings.common;

import com.ecom.javacodings.common.transfer.PageDTO;

import java.util.List;

@FunctionalInterface
public interface PageInterface {
    public List<Object> listObjects(PageDTO page);
}
