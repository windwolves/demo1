package com.leading.demo1.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {
    @Autowired
    private TestRestTemplate rest;

    @Test
    public void testUserListWithParameter() {
        HttpEntity<String> request = new HttpEntity<>("", new HttpHeaders());
        String urlStringWithParameter = "/UserList/withParameter?accessToken=token";
        String urlStringWithoutParameter = "/UserList/withParameter";
        ResponseEntity<String> response = rest.exchange(urlStringWithParameter, HttpMethod.GET,
                request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        rest.exchange(urlStringWithParameter, HttpMethod.GET,
                request, String.class);
        ResponseEntity<String> response3 = rest.exchange(urlStringWithParameter, HttpMethod.GET,
                request, String.class);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response3.getStatusCode());
        ResponseEntity<String> response4 = rest.exchange(urlStringWithoutParameter, HttpMethod.GET,
                new HttpEntity<>("", new HttpHeaders()), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response4.getStatusCode());

    }

    @Test
    public void testUserListWithHeader() throws InterruptedException {
        Thread.sleep(10000L);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("token");
        HttpEntity<String> request = new HttpEntity<>("", headers);
        String urlString = "/UserList/withHeader";
        ResponseEntity<String> response = rest.exchange(urlString, HttpMethod.GET,
                request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        rest.exchange(urlString, HttpMethod.GET,
                request, String.class);
        ResponseEntity<String> response3 = rest.exchange(urlString, HttpMethod.GET,
                request, String.class);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response3.getStatusCode());
        ResponseEntity<String> response4 = rest.exchange(urlString, HttpMethod.GET,
                new HttpEntity<>("", new HttpHeaders()), String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response4.getStatusCode());

    }
}
