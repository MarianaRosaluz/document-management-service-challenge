package com.clara.ops.challenge;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FlywayConfiguration {

    @PostConstruct
    public void run() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/document-management",
                        "postgres",
                        "root")
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
