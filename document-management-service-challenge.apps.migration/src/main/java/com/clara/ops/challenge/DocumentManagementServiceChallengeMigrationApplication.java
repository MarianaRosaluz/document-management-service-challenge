package com.clara.ops.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.clara.ops.challenge"})
public class DocumentManagementServiceChallengeMigrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(DocumentManagementServiceChallengeMigrationApplication.class, args);
  }
}
