package com.example.air_quality.mqtt.publisher;

import com.example.air_quality.mqtt.config.MqttPubSubConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class MqttPublisher extends MqttPubSubConfig implements MqttPublisherBase {

    private static final Logger logger = LoggerFactory.getLogger(MqttPublisher.class);

    /**
     * Private default constructor
     */
    private MqttPublisher() {
        clientId = "publisher";
        this.connect();
    }

    /**
     * Private constructor
     */
    private MqttPublisher(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        this.connect(broker, port, ssl, withUserNamePass);
    }

    /**
     * Factory method to get instance of MQTTPublisher
     *
     * @return MQTTPublisher
     */
    public static MqttPublisher getInstance() {
        return new MqttPublisher();
    }

    /**
     * Factory method to get instance of MQTTPublisher
     *
     * @param broker           address of broker (without port)
     * @param port             port of broker
     * @param ssl              ssl or not
     * @param withUserNamePass are username and password provided
     * @return MQTTPublisher
     */
    public static MqttPublisher getInstance(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        return new MqttPublisher(broker, port, ssl, withUserNamePass);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.main.java.com.example.air_quality.mqtt.publisher.MQTTPublisherBase#configurePublisher()
     */
    @Override
    protected void connect() {
        connect(this.broker,this.port,this.hasSSL,this.hasSSL);
    }

    /*
     * (non-Javadoc)
     * @see com.main.java.com.example.air_quality.mqtt.publisher.MQTTPublisherBase#publishMessage(java.lang.String, java.lang.String)
     */
    @Override
    public void publishMessage(String topic, String message) {

        try {
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());
            mqttmessage.setQos(this.qos);
            this.mqttClient.publish(topic, mqttmessage);
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.Throwable)
     */
    @Override
    public void connectionLost(Throwable arg0) {
        logger.info("Connection Lost");

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken)
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        logger.info("delivery completed");
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String, org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    @Override
    public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
        // Leave it blank for Publisher
    }

    /*
     * (non-Javadoc)
     * @see com.main.java.com.example.air_quality.mqtt.publisher.MQTTPublisherBase#disconnect()
     */
    @Override
    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }
    }
}
