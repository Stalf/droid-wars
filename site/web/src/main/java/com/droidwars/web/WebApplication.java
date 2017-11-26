package com.droidwars.web;

import com.droidwars.core.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.droidwars")
@Import({SecurityConfig.class, JpaConfig.class})
public class WebApplication {

    public static void main (String... args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
