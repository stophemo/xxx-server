package com.stp.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XxxApplication {

    public static void main(String[] args) {
        SpringApplication.run(XxxApplication.class, args);
    }
}
