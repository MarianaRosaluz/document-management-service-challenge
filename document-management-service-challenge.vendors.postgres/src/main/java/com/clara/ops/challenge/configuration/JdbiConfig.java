package com.clara.ops.challenge.configuration;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbiConfig {
    @Bean
    public static Jdbi jdbi(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        Jdbi jdbi = Jdbi.create(url, username, password);
        jdbi.installPlugins();
        return jdbi;
    }
}
