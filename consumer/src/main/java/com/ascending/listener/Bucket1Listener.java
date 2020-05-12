package com.ascending.listener;

import com.ascending.service.Mail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.time.LocalDateTime;

public class Bucket1Listener implements MessageListener {

    @Override
    public void onMessage(javax.jms.Message message) {
        try {
            System.out.println(((TextMessage)message).getText());
            JSONObject json = new JSONObject(((TextMessage)message).getText());

            String content = String.format("You uploaded %s (%s) at %s.",json.get("fileName"),json.get("s3key"),json.get("time"));

            Mail.send((String) json.get("email"),"New file uploaded notice",content);
            Thread.sleep(3000);
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
