package mqtt.publisher;

public interface MqttPublisherBase {
    /**
     * Publish message
     *
     * @param topic name of topic
     * @param jsonMessage content
     */
    void publishMessage(String topic, String jsonMessage);

    /**
     * Disconnect MQTT Client
     */
    void disconnect();

}
