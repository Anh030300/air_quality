//package com.example.air_quality.mqtt;
//
//import com.example.air_quality.mqtt.subscriber.MqttSubscriberBase;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageListener implements Runnable{
//
//    @Autowired
//    MqttSubscriberBase subscriber;
//
//    @Override
//    public void run() {
//        while(true) {
//            subscriber.subscribeMessage("sensor");
//        }
//    }
//
//}
