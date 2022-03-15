package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private List<String> sessionID;

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        sessionID =  responseEntity.getHeaders().get("Set-Cookie");
        return responseEntity.getBody();
    }

    public void addUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", String.join(";", sessionID));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity.getBody());
    }


    public void changeUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", String.join(";", sessionID));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        System.out.println(restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody());
    }

    public void deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", String.join(";", sessionID));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        System.out.println(restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class).getBody());
    }
}
