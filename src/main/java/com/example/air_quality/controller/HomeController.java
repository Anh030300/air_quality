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
    public String getHomePage(Model model) throws IOException {
        model.addAttribute("data",sensorDataService.findTheNewest() );

        return "homepage";
    }
    @GetMapping("/newest")
    public String getNewestData(Model model) throws IOException {
        model.addAttribute("data",sensorDataService.findTheNewest() );

        return "newest_data";
    }
    @GetMapping("/all")
    public String getAllData(Model model) throws IOException {
        model.addAttribute("list_data",sensorDataService.findAll() );

        return "all_data";
    }
}
