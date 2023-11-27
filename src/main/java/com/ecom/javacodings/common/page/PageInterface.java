package com.ecom.javacodings.common.page;

import java.util.List;

@FunctionalInterface
public interface PageInterface {
    public List<Object> listObjects(PageDTO page);
}
