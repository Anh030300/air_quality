package com.example.air_quality.controller;

import com.example.air_quality.connectESP.ConnectESP8266;
import com.example.air_quality.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    ConnectESP8266 connectESP8266;
    @GetMapping("/")
    public String getClientHomePage(Model model) throws IOException {
        SensorData sensorData = connectESP8266.getData();
        model.addAttribute("data",sensorData);
        return "homepage";
    }
}
