//package com.example.air_quality.mqtt.subscriber;
//
//import com.example.air_quality.mqtt.config.MqttPubSubConfig;
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.sql.Timestamp;
//
//@Component
//public class MqttSubscriber extends MqttPubSubConfig implements MqttSubscriberBase{
////    final private String clientId = "demoClient2";
//
//    private static final Logger logger = LoggerFactory.getLogger(MqttSubscriber.class);
//
//    public MqttSubscriber() {
//        this.clientId ="subscriber";
//        this.connect();
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
//     * Throwable)
//     */
//    @Override
//    public void connectionLost(Throwable cause) {
//        logger.info("Connection Lost");
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String,
//     * org.eclipse.paho.client.mqttv3.MqttMessage)
//     */
//    @Override
//    public void messageArrived(String topic, MqttMessage message) throws Exception {
//        // Called when a message arrives from the server that matches any
//        // subscription made by the client
//        String time = new Timestamp(System.currentTimeMillis()).toString();
//        System.out.println();
//        System.out.println("***********************************************************************");
//        System.out.println("Message Arrived at Time: " + time + "  Topic: " + topic + "  Message: "
//                + new String(message.getPayload()));
//        System.out.println("***********************************************************************");
//        System.out.println();
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho
//     * .client.mqttv3.IMqttDeliveryToken)
//     */
//    @Override
//    public void deliveryComplete(IMqttDeliveryToken token) {
//        // Leave it blank for subscriber
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * com.main.java.com.example.air_quality.mqtt.subscriber.MQTTSubscriberBase#subscribeMessage(java.
//     * lang.String)
//     */
//    @Override
//    public void subscribeMessage(String topic) {
//        try {
//            this.mqttClient.subscribe(topic, this.qos);
//        } catch (MqttException me) {
//            me.printStackTrace();
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see com.main.java.com.example.air_quality.mqtt.subscriber.MQTTSubscriberBase#disconnect()
//     */
//    public void disconnect() {
//        try {
//            this.mqttClient.disconnect();
//        } catch (MqttException me) {
//            logger.error("ERROR", me);
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see com.main.java.com.example.air_quality.mqtt.MQTTConfig#config(java.lang.String,
//     * java.lang.Integer, java.lang.Boolean, java.lang.Boolean)
//     */
//
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see com.main.java.com.example.air_quality.mqtt.MQTTConfig#config()
//     */
//    @Override
//    protected void connect() {
//
//        this.brokerUrl = this.TCP + this.broker + colon + this.port;
//        this.persistence = new MemoryPersistence();
//        this.connectionOptions = new MqttConnectOptions();
//        try {
//            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
//            this.connectionOptions.setCleanSession(true);
//            this.mqttClient.connect(this.connectionOptions);
//            this.mqttClient.setCallback(this);
//        } catch (MqttException me) {
//            me.printStackTrace();
//        }
//
//    }
//}
