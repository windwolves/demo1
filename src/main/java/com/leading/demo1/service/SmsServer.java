package com.leading.demo1.service;

import com.leading.demo1.domain.SmsObject;
import org.springframework.http.ResponseEntity;

public interface SmsServer {
    ResponseEntity<String> sendSMS(SmsObject smsObject);
}
