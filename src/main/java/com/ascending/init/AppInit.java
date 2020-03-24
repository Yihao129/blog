package com.ascending.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ascending")
public class AppInit {
    public static void main(String[] args) {
        SpringApplication.run(AppInit.class,args);
    }
}
