package com.ecom.javacodings.purchase.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Transaction {

    public Map<String, String> request(String url, Map<String, String> requestBody)
            throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String JSONBody = mapper.writeValueAsString(requestBody);

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

}
