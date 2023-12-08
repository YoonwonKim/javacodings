package com.ecom.javacodings.external.purchase;

import com.ecom.javacodings.common.transfer.ItemDTO;
import com.ecom.javacodings.common.transfer.MemberDTO;
import com.ecom.javacodings.common.transfer.MemberPaymentDTO;
import com.ecom.javacodings.common.transfer.OrderDTO;
import com.ecom.javacodings.customer.access.items.ItemDAO;
import com.ecom.javacodings.customer.access.members.MemberInfoDAO;
import com.ecom.javacodings.customer.access.members.MemberPaymentDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class payUpService implements PurchaseService {

    @Value("${purchase.payup.merchant-id}") String MERCHANT_ID;
    @Value("${purchase.payup.cert-key}") String CERT_KEY;

    @Value("${purchase.payup.test-url}") String PAYUP_URL;
    @Value("${purchase.kakao.test-url}") String KAKAO_URL;

    @Autowired ItemDAO itemDAO;
    @Autowired MemberInfoDAO memberInfoDAO;
    @Autowired MemberPaymentDAO paymentDAO;

    // Region Processing

//    @Override
//    public String requestPurchase(OrderDTO order, MemberDTO member)
//            throws NoSuchAlgorithmException, IOException, InterruptedException {
//        // ? create a map for body
//        Map<String, String> requestBody = new HashMap<>();
//        ItemDTO item = itemDAO.findItemByItemId(order.getItem_id());
//        String amount = String.valueOf(order.getQuantity() * item.getPrice());
//        LocalDateTime date = LocalDateTime.now();
//        String dateString  = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        String signature   = MERCHANT_ID + "|"
//                + order.getOrder_id() + "|"
//                + amount + "|"
//                + CERT_KEY + "|"
//                + dateString;
//
//        body.put("timestamp", dateString);
//        body.put("signature", getSignature(signature));
//        // 결제 정보
//        body.put("orderNumber", order.getOrder_id());
//        body.put("itemName", item.getLabel());
////        body.put("cardNo", member.getCard_no());
////        body.put("cardPw", member.getCard_pw());
////        body.put("expireMonth", member.getExpire_month());
////        body.put("expireYear",  member.getExpire_year());
//        body.put("cardNo", "9490191412341234");
//        body.put("cardPw", "00");
//        body.put("expireMonth", "05");
//        body.put("expireYear",  "19");
//
//        body.put("quota", "0");
//        body.put("amount", amount);
//        // 사용자 정보
//        body.put("userName", member.getName());
//        body.put("userId", member.getMember_id());
//        body.put("birthday", member.getBirth());
//        body.put("mobileNumber", "010"+member.getPhone());
//        body.put("userEmail", member.getEmail());
//        // 알림 설정
//        body.put("kakaoSend", "Y");
//
//
//        String url = PAYUP_URL + MERCHANT_ID + "/keyin2";
//        //? build and send the request
//        Map<String, String> responseBody = protocol(url, body);
//        return responseBody.get("transactionId");
//    }

    @Override
    public Map<String, String> requestOrder(OrderDTO order, ItemDTO item, MemberDTO member)
            throws NoSuchAlgorithmException, IOException, InterruptedException {
        String amount = String.valueOf(order.getQuantity() * item.getPrice());
        LocalDateTime date = LocalDateTime.now();
        String dateString  = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String signature   = MERCHANT_ID + "|"
                + order.getOrder_id() + "|"
                + amount + "|"
                + CERT_KEY + "|"
                + dateString;

        RequestDTO requestBody = new RequestDTO();
        requestBody.setTimestamp(dateString);
        requestBody.setSignature(getSignature(signature));

        // 결제 정보
        ItemDTO itemData = itemDAO.findByItemId(order.getItem_id());
        requestBody.setOrder(order, itemData.getPrice());

        // 결제수단 정보
        MemberPaymentDTO paymentData = paymentDAO.findByMemberId(member.getMember_id());
        requestBody.setPayment(paymentData);

        // 사용자 정보
        MemberDTO memberData = memberInfoDAO.findByMemberId(member.getMember_id());
        requestBody.setMember(memberData);

        //? build and send the request
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestMap = mapper.convertValue(requestBody, Map.class);

        String url = PAYUP_URL + MERCHANT_ID + "/order";
        Map<String, String> responseBody = protocol(url, requestMap);
        return responseBody;
    }

    @Override
    public Map<String, String> confirmOrder(MemberDTO member, Map<String, String> params)
            throws NoSuchAlgorithmException, IOException, InterruptedException {
        String url = PAYUP_URL + params.get("order_idxx") + "/pay";

        //? create a map for body
        Map<String, String> body = new HashMap<>();
        body.putAll(params);

        //? build and send the request
        Map<String, String> responseBody = protocol(url, body);
        return responseBody;
    }

    @Override
    public String requestCancel(OrderDTO order, ItemDTO item)
            throws NoSuchAlgorithmException, IOException, InterruptedException {
        String url = PAYUP_URL + MERCHANT_ID + "/cancel2";

        //? create a map for body
        Map<String, String> body = new HashMap<>();
        String signature   = MERCHANT_ID + "|" + order.getTransaction_id() + "|" + CERT_KEY;

        body.put("signature", getSignature(signature));
        body.put("transactionId", order.getTransaction_id());

        //? build and send the request
        Map<String, String> responseBody = protocol(url, body);
        return responseBody.get("responseMessage");
    }

    // Region Kakao API

    @Override
    public Map<String, String> requestKakaoOrder(OrderDTO order, ItemDTO item, MemberDTO member)
            throws NoSuchAlgorithmException, IOException, InterruptedException {
        String url = KAKAO_URL + MERCHANT_ID + "/order";
//
//        LocalDateTime date = LocalDateTime.now();
//        String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        int amount = order.getQuantity() * item.getPrice();
//        String signature = MERCHANT_ID + "|"
//                + order.getOrder_id() + "|"
//                + amount + "|"
//                + CERT_KEY + "|"
//                + dateString;
//
//        Map<String, String> requestBody = new HashMap<>();
//        // Order Info
//        requestBody.put("orderNumber", order.getOrder_id());
//        requestBody.put("amount", String.valueOf(amount));
//        // Item Info
//        requestBody.put("itemName", item.getLabel());
//        // Member Info
//        requestBody.put("userName", member.getName());
//        requestBody.put("userMail", member.getEmail());
//        requestBody.put("mobileNumber", member.getPhone());
//        // Transaction Info
//        requestBody.put("userAgent", "WP");
//        requestBody.put("returnUrl", "dummy");
//        requestBody.put("timestamp", dateString);
//        requestBody.put("cashbillYn", "N");
//        requestBody.put("signature", getSignature(signature));

//        //? build and send the request
//        Map<String, String> responseBody = protocol(url, requestBody);
//        return responseBody;
        return null;
    }

    @Override
    public Map<String, String> requestKakaoConfirm(ConfirmDTO confirmTransaction)
            throws IOException, InterruptedException {
        String url = KAKAO_URL + MERCHANT_ID + "/pay";

        Map<String, String> requestBody  = new HashMap<>();
        Map<String, String> responseBody;

        //? Set Request Body
        ObjectMapper mapper = new ObjectMapper();
        requestBody = mapper.convertValue(confirmTransaction, new TypeReference<Map<String, String>>() {});

        //? build and send the request
        responseBody = protocol(url, requestBody);
        return responseBody;
    }

    @Override
    public Map<String, String> requestKakaoCancel(OrderDTO order, String reason)
            throws IOException, InterruptedException, NoSuchAlgorithmException {
        String url = KAKAO_URL + MERCHANT_ID + "/cancel";

        Map<String, String> requestBody  = new HashMap<>();
        Map<String, String> responseBody = new HashMap<>();

        //? Set Request Body
        String signature = MERCHANT_ID + "|" + order.getTransaction_id() + "|" + CERT_KEY;
        requestBody.put("transactionId", order.getTransaction_id());
        requestBody.put("cancelReason", reason);
        requestBody.put("signature", getSignature(signature));

        //? build and send the request
        responseBody = protocol(url, requestBody);
        return responseBody;
    }

    // End Region Kakao API
    // Region HTTP Request

    private Map<String, String> protocol(String url, Map<String, String> body)
            throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String JSONBody = mapper.writeValueAsString(body);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(JSONBody))
                .header("Content-Type", "application/json; charset=UTF-8")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, String>  responseBody = mapper.readValue(
                response.body(),
                new TypeReference<Map<String, String>>() {}
        );
        return responseBody;
    }

    // End Region HTTP Request
    // Region Encryption

    private String getSignature(String originalString)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));

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

    // End Region Encryption
}
