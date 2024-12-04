package com.security.jwt;

import com.security.jwt.infrastructure.config.security.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class SptingSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SptingSecurityJwtApplication.class, args);
    }

}
