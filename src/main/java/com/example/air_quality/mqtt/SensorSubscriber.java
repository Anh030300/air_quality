package com.example.air_quality.mqtt;

import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.mqtt.subscriber.MqttSubscriber;
import com.example.air_quality.service.SensorDataService;
import org.bson.BsonDocument;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorSubscriber extends MqttSubscriber {

    SensorDataService sensorDataService;

    @Autowired
    public SensorSubscriber(SensorDataService sensorDataService) {
        this.clientId ="SensorSubscriber";
        this.connect();
        this.sensorDataService = sensorDataService;
    }

    @Override
    protected void handleArrivedMessage(String arrivedMessage) {
        JsonObject dataFromSensor = new JsonObject(arrivedMessage);
        BsonDocument bsonDocument = dataFromSensor.toBsonDocument();

        //parse bson object to sensorDataEntity and save to database
        String temp = bsonDocument.get("temperature").asString().getValue();
        String humidity = bsonDocument.get("humidity").asString().getValue();
        // TODO unsuccessful save
        sensorDataService.save(new SensorDataEntity(temp,humidity));
        System.out.println("After Parsing: temp: "+temp+", humidity: "+humidity);
    }
}
