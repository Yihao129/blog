package com.ascending.init;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.ascending.config.AWSConfig;
import com.ascending.listener.Bucket1Listener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.*;

@SpringBootApplication(scanBasePackages = "com.ascending")
public class AppInit {

    public static void main(String[] args) throws InterruptedException, JMSException {
        SpringApplication.run(AppInit.class,args);

        AWSConfig awsConfig = new AWSConfig();
        AmazonSQS amazonSQS = awsConfig.getAmazonSQS();

        AmazonSQSClientBuilder builder = AmazonSQSClientBuilder.standard().withRegion(System.getProperty("aws.sqs.region"));

        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard().withRegion(System.getProperty("aws.sqs.region"))
        );

        SQSConnection connection = connectionFactory.createConnection();

        // Create the nontransacted session with AUTO_ACKNOWLEDGE mode
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a consumer for the 'MyQueue'.
        MessageConsumer consumer = session.createConsumer(session.createQueue(System.getProperty("aws.sqs.name")));

        // Instantiate and set the message listener for the consumer.
        consumer.setMessageListener(new Bucket1Listener());

        // Start receiving incoming messages.
        connection.start();

    }

}
