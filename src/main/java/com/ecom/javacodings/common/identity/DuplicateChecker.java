package com.ecom.javacodings.common.identity;

import com.ecom.javacodings.common.page.PageDTO;

import java.util.List;

@FunctionalInterface
public interface DuplicateChecker {
    Boolean check(String generatedId);
}
