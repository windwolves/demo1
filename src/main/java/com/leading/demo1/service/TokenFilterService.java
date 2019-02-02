package com.leading.demo1.service;

import com.leading.demo1.configuration.Configuration;
import com.leading.demo1.token.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenFilterService {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilterService.class);
    private static final Map<String, List<AccessToken>> tokenMap = new ConcurrentHashMap<>();
    private Integer accessThreshold;
    private Long intervalThreshold;
    private Configuration config;

    @Autowired
    public TokenFilterService(Configuration config) {
        this.config = config;
        init();
    }

    private void init() {

        if (config.getAccessThreshold() != null) {
            accessThreshold = config.getAccessThreshold();
        } else {
            logger.warn("Cannot get accessThreshold from config, will use default value");
            accessThreshold = 2;
        }
        if (config.getIntervalThreshold() != null) {
            intervalThreshold = config.getIntervalThreshold();
        } else {
            logger.warn("Cannot get intervalThreshold from config, will use default value");
            intervalThreshold = 5000L;
        }

    }

    public boolean isLimited(AccessToken token) {
        boolean isLimited = true;
        String accessToken = token.getToken();
        List<AccessToken> tokenList = new ArrayList<>();
        synchronized (tokenMap) {
            if (tokenMap.containsKey(accessToken)) {
                tokenList = tokenMap.get(accessToken);
            }
            if (tokenList.size() < accessThreshold) {
                tokenList.add(token);
                tokenMap.put(accessToken, tokenList);
                isLimited = false;
            } else {
                long firstAccessTime = tokenList.get(0).getAccessTimeStampUTC();
                if (token.getAccessTimeStampUTC() - firstAccessTime > intervalThreshold) {
                    isLimited = false;
                }
                tokenList.remove(0);
                tokenList.add(token);
            }
        }
        return isLimited;
    }

}
