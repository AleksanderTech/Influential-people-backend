package com.alek.influentialpeople.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Properties {

    @Autowired
    private Environment env;

    public String getConfig(String configKey) {
        return env.getProperty(configKey);
    }
}