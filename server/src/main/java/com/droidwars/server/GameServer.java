package com.droidwars.server;

import com.droidwars.game.engine.factory.game.AbstractGameInstanceFactory;
import com.droidwars.game.engine.factory.game.SimpleGameInstanceServerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class GameServer {

    private final ConfigProperties configProperties;

    @Bean
    public AbstractGameInstanceFactory gameFactory() {
        return new SimpleGameInstanceServerFactory(configProperties.getFileStorage().getRootPath());
    }

    @Autowired
    public GameServer(ConfigProperties configProperties) {
        this.configProperties = configProperties;

        log.info("Configuration loaded: {}", configProperties.toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(GameServer.class);
    }

}

