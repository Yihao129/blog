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

    static class A{
        String name;
        String say;

        public A(String name, String say) {
            this.name = name;
            this.say = say;
        }

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    ", say='" + say + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSay() {
            return say;
        }

        public void setSay(String say) {
            this.say = say;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        System.out.println(LocalDateTime.now().toString());

    }

}
