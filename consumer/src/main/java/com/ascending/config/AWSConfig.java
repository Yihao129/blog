package com.ascending.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Bean
    public AmazonSQS getAmazonSQS(){
        return AmazonSQSClientBuilder.standard()
                .withRegion(System.getProperty("aws.sqs.region"))
                .build();
    }

}
