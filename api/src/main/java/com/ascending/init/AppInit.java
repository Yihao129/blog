package com.ascending.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.ascending")
@ServletComponentScan(basePackages = {"com.ascending.filter"})
public class AppInit extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppInit.class,args);
    }

}

