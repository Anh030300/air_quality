package mqtt.subscriber;

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

    /**
     * get the received message from the subscribed topic
     * @return received message ({@code String})
     */
    String getArrivedMessage();
}
