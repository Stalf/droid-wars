package com.droidwars.web.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesUtils {

    @Getter
    private static final Properties messageProperties;

    static {
        messageProperties = new Properties();
        try {
            messageProperties.load(ClassUtils.getDefaultClassLoader().getResourceAsStream("messages.properties"));
        } catch (IOException e) {
            log.error("Error loading message.properties", e);
        }
    }

}
