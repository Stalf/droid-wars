package com.droidwars.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GameServer {

    private final ConfigProperties configProperties;

    @Autowired
    public GameServer(ConfigProperties configProperties) {
        this.configProperties = configProperties;

        log.info("Configuration loaded: {}", configProperties.toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(GameServer.class);
    }

}

