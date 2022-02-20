package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mqtt.SensorPublisher;

public class DataController implements Runnable {
    public SensorPublisher sensorPublisher = new SensorPublisher();
    public long currentTime ;
    public long preTime ;
    public String temp;
    public String humi;
    public long time;

    @Override
    public void run() {
        while(true) {
            currentTime = System.currentTimeMillis();
            if (currentTime - preTime >= time*1000) {
                String topic = "sensor";
                String message = "{\"temperature\":\"" + temp + "\",\"humidity\":\"" + humi + "\"}";
                sensorPublisher.publishMessage(topic, message);
                preTime = currentTime;
            }
        }
    }
}
