package com.ascending.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Value("${aws.sqs.name}")
    private String queName;

    @Autowired
    private AmazonSQS sqs;

    public void sendMessage(String message, int delay){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(getQueUrl(queName))
                .withMessageBody(message)
                .withDelaySeconds(delay);
        sqs.sendMessage(send_msg_request);
    }

    private String getQueUrl(String name){
        return sqs.getQueueUrl(name).getQueueUrl();
    }


}
