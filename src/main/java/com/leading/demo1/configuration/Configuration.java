package com.leading.demo1.configuration;

import java.util.Map;

public interface Configuration {
    Integer getAccessThreshold();
    Long getIntervalThreshold();
    Map<String, String> getTwilioCredential();
    String getTextLocalApiKey();
}
