package com.ascending.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AWSConfig {

    public AmazonSQS getAmazonSQS(){
        return AmazonSQSClientBuilder.standard()
                .withRegion(System.getProperty("aws.sqs.region"))
                .build();
    }

}
