package com.leading.demo1.configuration;

import com.leading.demo1.commons.Environment;
import com.leading.demo1.service.TokenFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

import static com.leading.demo1.commons.Constants.*;

@Component
public class ConfigurationCloud implements Configuration {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationCloud.class);
    private Properties properties;
    public ConfigurationCloud() {
        init();
    }

    private void init() {
        properties = new Properties();
        try {
            properties.load(TokenFilterService.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.error("Cannot load default config property file");
        }
    }

    @Override
    public Integer getAccessThreshold() {
        if (Environment.getEnvironmentServices(KEY_ACCESS_THRESHOLD) != null) {
            return Integer.valueOf(System.getenv(KEY_ACCESS_THRESHOLD));
        } else if (properties.getProperty(KEY_ACCESS_THRESHOLD) != null){
            return Integer.valueOf(properties.getProperty(KEY_ACCESS_THRESHOLD));
        }
        return null;
    }

    @Override
    public Long getIntervalThreshold() {
        if (Environment.getEnvironmentServices(KEY_INTERVAL_THRESHOLD) != null) {
            return Long.valueOf(System.getenv(KEY_INTERVAL_THRESHOLD));
        } else if (properties.getProperty(KEY_INTERVAL_THRESHOLD) != null){
            return Long.valueOf(properties.getProperty(KEY_INTERVAL_THRESHOLD));
        }
        return null;
    }

}
