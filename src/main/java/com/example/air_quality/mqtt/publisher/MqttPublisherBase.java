package com.example.air_quality.mqtt.publisher;

public interface MqttPublisherBase {
    /**
     * Publish message
     *
     * @param topic name of topic
     * @param jsonMessage content
     */
    public void publishMessage(String topic, String jsonMessage);

    /**
     * Disconnect MQTT Client
     */
    public void disconnect();

}
