package com.example.air_quality.controller;

import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    SensorDataService sensorDataService ;
    @GetMapping("/")
    public String getHomePage(Model model) throws IOException {
        return "home";
    }
    @GetMapping("/charts")
    public String getNewestData(Model model) throws IOException {
        List<String> tempList = new ArrayList<>();
        List<String> humiList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<SensorDataEntity> sensorDataEntities=sensorDataService.findTop10();
        for(int i = sensorDataEntities.size()-1; i >=0;i--)
        {
            tempList.add(sensorDataEntities.get(i).getTemp());
            humiList.add(sensorDataEntities.get(i).getHumidity());
       //     dateList.add(sensorDataEntities.get(i).convertDate());
            dateList.add("1");
        }
        model.addAttribute("top10temp",tempList);
        model.addAttribute("top10humi",humiList);
        model.addAttribute("top10date",dateList);

        return "charts";
    }
    @GetMapping("/all")
    public String getAllData(Model model) throws IOException {
        model.addAttribute("list_data",sensorDataService.findAll() );

        return "all_data";
    }
    @GetMapping("/prediction")
    public String members(Model model) throws IOException {
        return "prediction";
    }
}
