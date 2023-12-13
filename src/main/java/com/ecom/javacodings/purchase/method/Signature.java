package com.ecom.javacodings.purchase.method;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signature {

    public String encrypt(String[] dataList)
            throws NoSuchAlgorithmException {
        MessageDigest hashFunction = MessageDigest.getInstance("SHA-256");
        String decryptedSignature = String.join("|", dataList);

        byte[] encodedHash = hashFunction.digest(
                decryptedSignature.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (int i = 0; i < encodedHash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedHash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
