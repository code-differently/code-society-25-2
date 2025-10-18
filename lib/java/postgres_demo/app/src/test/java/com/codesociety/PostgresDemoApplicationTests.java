package com.codesociety;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PostgresDemoApplicationTests {

    @Test
    void contextLoads() {
        // This test verifies that the Spring application context loads successfully
    }
}
