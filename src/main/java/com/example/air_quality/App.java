package com.example.air_quality;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
public class App {
    public static void main(String[] args) {
    String subTopic = "sensor";
    String pubTopic = "sensor";
    String username = "springserver";
    String password = "Springserver2394";
    String content = "Hello World";
    int qos = 2;
//    String broker = "tcp://broker.hivemq.com:1883";
    String broker = "ssl://3d31fa520d954a01ada2ce10fb61ce53.s2.eu.hivemq.cloud:8883";
    String clientId = "ACLIENT";
    MemoryPersistence persistence = new MemoryPersistence();
    try {
        MqttClient client = new MqttClient(broker, clientId,
                persistence);
// MQTT connection option
        MqttConnectOptions connOpts = new MqttConnectOptions();
// retain session
        connOpts.setCleanSession(true);
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());
// set callback
        client.setCallback(new OnMessageCallback());
// establish a connection
        System.out.println("Connecting to broker: " + broker);
        client.connect(connOpts);
        System.out.println("Connected");
        System.out.println("Publishing message: " + content);
// Subscribe
        client.subscribe(subTopic);
// Required parameters for message publishing
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        client.publish(pubTopic, message);
        System.out.println("Message published");
        client.disconnect();
        System.out.println("Disconnected");
        client.close();
        System.exit(0);
    }
    catch (MqttException me) {
        me.printStackTrace();
    }
}
}