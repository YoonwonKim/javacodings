package com.ecom.javacodings.common.policies;

public enum OrderPolicies {
    ID_LENGTH(30);

    private int orderPolicies;

    private OrderPolicies(int orderPolicies) {
        this.orderPolicies = orderPolicies;
    }

    public int getOrderPolicies() {
        return orderPolicies;
    }
}
