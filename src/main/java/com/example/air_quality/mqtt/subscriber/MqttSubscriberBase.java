package com.example.air_quality.mqtt.subscriber;

public interface MqttSubscriberBase {

    /**
     * Subscribe message
     *
     * @param topic
     */
    public void subscribeMessage(String topic);

    /**
     * Disconnect MQTT Client
     */
    public void disconnect();
}