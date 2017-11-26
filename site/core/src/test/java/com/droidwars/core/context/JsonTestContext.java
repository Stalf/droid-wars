package com.droidwars.core.context;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.TestPropertySource;

@SpringBootConfiguration
@EntityScan("com.droidwars.core.entity")
@TestPropertySource(locations="classpath:core-test.properties")
public class JsonTestContext {
}
