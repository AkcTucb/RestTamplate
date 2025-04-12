package ru.Akctucb.springmvc;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class App {
    private static final String URL = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);


        String sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);


        User user = new User(3L, "James", "Brown", (byte) 25);
        HttpEntity<User> postRequest = new HttpEntity<>(user, headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(URL, postRequest, String.class);
        String codePart1 = postResponse.getBody();
        System.out.println("Code part 1: " + codePart1);


        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> putRequest = new HttpEntity<>(user, headers);
        ResponseEntity<String> putResponse = restTemplate.exchange(URL, HttpMethod.PUT, putRequest, String.class);
        String codePart2 = putResponse.getBody();
        System.out.println("Code part 2: " + codePart2);


        HttpEntity<User> deleteRequest = new HttpEntity<>(headers);
        ResponseEntity<String> deleteResponse = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, deleteRequest, String.class);
        String codePart3 = deleteResponse.getBody();
        System.out.println("Code part 3: " + codePart3);


        String resultCode = codePart1 + codePart2 + codePart3;
        System.out.println("Final code: " + resultCode);
    }
}

