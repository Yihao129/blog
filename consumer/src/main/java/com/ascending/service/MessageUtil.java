package com.ascending.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageUtil {

    private AmazonSQS amazonSQS = null;
    private String queUrl = null;

    public MessageUtil(@Autowired AmazonSQS amazonSQS){
        this.amazonSQS = amazonSQS;
        queUrl = amazonSQS.getQueueUrl(System.getProperty("aws.sqs.name")).getQueueUrl();
    }

    public List<Message> getMessage(){
        return amazonSQS.receiveMessage(queUrl).getMessages();
    }

    public int deleteMessage(String handle){
        amazonSQS.deleteMessage(queUrl,handle);
        return 1;
    }


}
