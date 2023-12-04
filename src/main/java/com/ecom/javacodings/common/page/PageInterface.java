package com.ecom.javacodings.common.page;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface PageInterface {
    List<Object> listObjects(Map<String, Object> params);
}
