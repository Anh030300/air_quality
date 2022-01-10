package com.example.air_quality.controller;

import com.example.air_quality.connectESP.ConnectESP8266;
import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.service.SensorDataService;
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
    SensorDataService sensorDataService ;
    @GetMapping("/")
    public String getClientHomePage(Model model) throws IOException {
        //model.addAttribute("data", sensorDataService.findTheNewest());
        model.addAttribute("data",ConnectESP8266.getData() );
        return "homepage";
    }
}
