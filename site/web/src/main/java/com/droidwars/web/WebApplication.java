package com.droidwars.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories("com.droidwars.core.repository")
@ComponentScan("com.droidwars")
@EntityScan(basePackages = "com.droidwars.core.entity")
@Import(SecurityConfiguration.class)
public class WebApplication {

    public static void main (String... args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
