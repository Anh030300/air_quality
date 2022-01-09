package com.example.air_quality.mqtt.subscriber;

public interface MqttSubscriberBase {

    /**
     * Subscribe message
     *
     * @param topic name of topic
     */
    void subscribeMessage(String topic);

    /**
     * Disconnect MQTT Client
     */
    void disconnect();
}
