package com.example.air_quality.mqtt.publisher;

public interface MqttPublisherBase {

    /**
     * Publish message
     *
     * @param topic
     * @param message
     */
    public void publishMessage(String topic, String message);

    /**
     * Disconnect MQTT Client
     */
    public void disconnect();

}
