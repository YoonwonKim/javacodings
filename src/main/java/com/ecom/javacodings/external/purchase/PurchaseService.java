package com.ecom.javacodings.external.purchase;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface PurchaseService {
    String requestPurchase(OrderDTO order, ItemDTO item, MemberDTO member)
            throws NoSuchAlgorithmException, IOException, InterruptedException;

    String requestCancel(OrderDTO order, ItemDTO item)
            throws NoSuchAlgorithmException, IOException, InterruptedException;
}
