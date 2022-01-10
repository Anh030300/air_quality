package com.example.air_quality.controller;

import com.example.air_quality.mqtt.publisher.MqttPublisherBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DemoRestController {

    @Autowired
    MqttPublisherBase publisher;

    @RequestMapping(value = "/mqtt/send", method = RequestMethod.POST)
    public String index(@RequestBody String data) {
        publisher.publishMessage("sensor", data);
        return "Message sent to Broker";
    }

}
