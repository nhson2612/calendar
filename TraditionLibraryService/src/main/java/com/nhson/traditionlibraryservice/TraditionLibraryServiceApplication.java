package com.nhson.traditionlibraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TraditionLibraryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraditionLibraryServiceApplication.class, args);
    }

}
