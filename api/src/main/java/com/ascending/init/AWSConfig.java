package com.ascending.init;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AWSConfig {
    @Value("${aws.sqs.region}")
    private String REGION;

    @Bean
    public AmazonS3 getAmazonS3(){
        return AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .build();
    }

    @Bean
    public AmazonSQS getAmazonSQS(){
        return AmazonSQSClientBuilder.standard()
                .withRegion(REGION)
                .build();
    }

}
