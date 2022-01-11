package com.example.air_quality.mqtt;

import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.mqtt.subscriber.MqttSubscriber;
import com.example.air_quality.service.SensorDataService;
import org.bson.BsonDocument;
import org.bson.json.JsonObject;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class SensorSubscriber extends MqttSubscriber {

    SensorDataService sensorDataService;

    @Autowired
    public SensorSubscriber(SensorDataService sensorDataService) {
        this.clientId ="SensorSubscriber";
        this.connect();
        this.sensorDataService = sensorDataService;
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

        // parse message to bson object
        JsonObject dataFromSensor = new JsonObject(arrivedMessage);
        BsonDocument bsonDocument = dataFromSensor.toBsonDocument();

        //parse bson object to sensorDataEntity and save to database
        String temp = bsonDocument.get("temperature").asString().getValue();
        String humidity = bsonDocument.get("humidity").asString().getValue();
        // TODO unsuccessful save
        sensorDataService.save(new SensorDataEntity(temp,humidity));

        System.out.println();
        System.out.println("***********************************************************************");
        System.out.println("Message Arrived at Time: " + time + "  Topic: " + topic + "  Message: "
                + arrivedMessage);
        System.out.println("***********************************************************************");
        System.out.println();
        System.out.println("After Parsing: temp: "+temp+", humidity: "+humidity);
    }

}
