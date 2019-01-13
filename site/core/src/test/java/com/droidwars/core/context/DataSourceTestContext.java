package com.droidwars.core.context;

import com.droidwars.core.security.JwtTokenUtil;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;

@SpringBootConfiguration
@EnableJpaRepositories("com.droidwars.core.repository")
@EntityScan("com.droidwars.core.entity")
@ComponentScan("com.droidwars.core.service")
public class DataSourceTestContext {

    @Bean
    public AuthenticationManager authenticationManager() {
        return mock(AuthenticationManager.class);
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return mock(JwtTokenUtil.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return mock(PasswordEncoder.class);
    }
}
