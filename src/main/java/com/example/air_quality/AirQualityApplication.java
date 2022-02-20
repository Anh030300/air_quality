package com.example.air_quality;

import com.example.air_quality.mqtt.SensorSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AirQualityApplication{

    public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(AirQualityApplication.class, args);
        SensorSubscriber sensorSubscriber = context.getBean(SensorSubscriber.class);
        sensorSubscriber.subscribeMessage("sensor");
    }

}