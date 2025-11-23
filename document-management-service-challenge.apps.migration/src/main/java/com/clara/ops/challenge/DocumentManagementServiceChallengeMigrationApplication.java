package com.clara.ops.challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocumentManagementServiceChallengeMigrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(DocumentManagementServiceChallengeMigrationApplication.class, args);



  }
}
