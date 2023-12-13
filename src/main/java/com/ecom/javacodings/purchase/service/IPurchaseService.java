package com.ecom.javacodings.purchase.service;

import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.purchase.data.PurchaseData;

import java.util.Map;

public interface IPurchaseService {

    Map<String, String> request(OrderDTO order);
    Map<String, String> purchase(String ordr_idxx, PurchaseData purchaseData);

    Map<String, String> cancel();
    Map<String, String> partialCancel();

}
