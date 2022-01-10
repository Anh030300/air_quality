package com.example.air_quality.config;

import com.example.air_quality.mqtt.subscriber.MqttSubscriber;
import com.example.air_quality.mqtt.subscriber.MqttSubscriberBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageListener implements Runnable{

    MqttSubscriberBase subscriber = new MqttSubscriber();

    @Override
    public void run() {
        while(true) {
            subscriber.subscribeMessage("sensor");
        }

    }

}
