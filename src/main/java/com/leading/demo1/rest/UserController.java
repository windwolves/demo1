package com.leading.demo1.rest;


import com.leading.demo1.domain.User;
import com.leading.demo1.service.TokenFilterService;
import com.leading.demo1.token.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    private TokenFilterService tokenFilterService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(TokenFilterService tokenFilterService) {
        this.tokenFilterService = tokenFilterService;
    }

    @GetMapping("/UserList/withHeader")
    //fetch the access token from http headers
    public ResponseEntity<String> userList(@RequestHeader HttpHeaders headers) {
        if (!headers.containsKey("Authorization")) {
            logger.warn("No Authorization provided");
            return new ResponseEntity<>("No accessToken provided", HttpStatus.UNAUTHORIZED);
        }
        String tokenString = Objects.requireNonNull(headers.get("Authorization")).get(0).replaceFirst("Bearer ","");
        if (filter(tokenString)) {
            logger.warn("Too many request for this token.");
            return new ResponseEntity<>("Too Many Requests", HttpStatus.TOO_MANY_REQUESTS);
        }
        logger.info("Return empty user list.");
        return new ResponseEntity<>(fetchUserList().toString(), HttpStatus.OK);
    }

    @GetMapping("/UserList/withParameter")
    //fetch the access token from parameters
    //Using parameter can let the application be tested via web browser, which is easier.
    public ResponseEntity<String> userList(@RequestParam String accessToken) {
        if (filter(accessToken)) {
            logger.warn("Too many request for this token.");
            return new ResponseEntity<>("Too Many Requests", HttpStatus.TOO_MANY_REQUESTS);
        }
        logger.info("Return empty user list.");
        return new ResponseEntity<>(fetchUserList().toString(), HttpStatus.OK);
    }

    private List<User> fetchUserList() {
        return Collections.emptyList();
    }

    private boolean filter(String accessToken) {
        AccessToken token = new AccessToken().setToken(accessToken).setAccessTimeStampUTC(System.currentTimeMillis());
        return tokenFilterService.isLimited(token);
    }
}
