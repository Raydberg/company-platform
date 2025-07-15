package com.company.platform.msvccompanies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsvcCompaniesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcCompaniesApplication.class, args);
    }
}
