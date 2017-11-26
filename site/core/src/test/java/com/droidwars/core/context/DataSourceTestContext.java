package com.droidwars.core.context;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootConfiguration
@EnableJpaRepositories("com.droidwars.core.repository")
@EntityScan("com.droidwars.core.entity")
@ComponentScan("com.droidwars.core.service")
@TestPropertySource(locations="classpath:core-test.properties")
public class DataSourceTestContext {
}
