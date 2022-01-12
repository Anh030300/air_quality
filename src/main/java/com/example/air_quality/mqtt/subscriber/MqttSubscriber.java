package com.example.air_quality.mqtt.subscriber;

import com.example.air_quality.mqtt.config.MqttPubSubConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public abstract class MqttSubscriber extends MqttPubSubConfig implements MqttSubscriberBase{

    private static final Logger logger = LoggerFactory.getLogger(MqttSubscriber.class);
    private String arrivedMessage;

    public MqttSubscriber() {
        this.clientId ="subscriber";
        this.connect();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
     * Throwable)
     */
    @Override
    public void connectionLost(Throwable cause) {
        logger.info("Connection Lost");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String,
     * org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Called when a message arrives from the server that matches any
        // subscription made by the client
        String time = new Timestamp(System.currentTimeMillis()).toString();
        arrivedMessage = new String(message.getPayload());
        handleArrivedMessage(arrivedMessage);
        System.out.println();
        System.out.println("***********************************************************************");
        System.out.println("Message Arrived at Time: " + time + "  Topic: " + topic + "  Message: "
                + arrivedMessage);
        System.out.println("***********************************************************************");
        System.out.println();
    }

    protected abstract void handleArrivedMessage(String arrivedMessage);

    @Override
    public String getArrivedMessage() {
        return arrivedMessage;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho
     * .client.mqttv3.IMqttDeliveryToken)
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Leave it blank for subscriber
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.main.java.com.example.air_quality.mqtt.subscriber.MQTTSubscriberBase#subscribeMessage(java.
     * lang.String)
     */
    @Override
    public void subscribeMessage(String topic) {
        try {
            this.mqttClient.subscribe(topic, this.qos);
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.main.java.com.example.air_quality.mqtt.subscriber.MQTTSubscriberBase#disconnect()
     */
    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.main.java.com.example.air_quality.mqtt.MQTTConfig#config()
     */
    @Override
    protected void connect() {
        logger.info("Connecting to broker:"+broker+", port:"+port+", hasSSL:"+hasSSL);
        connect(this.broker,this.port,this.hasSSL,this.hasSSL);
    }
}
