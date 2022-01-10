package com.example.air_quality;

import com.example.air_quality.connectESP.ConnectESP8266;
import com.example.air_quality.service.SensorDataService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
public class GetDataThread implements Runnable {
    SensorDataService sensorDataService= new SensorDataService();
    @SneakyThrows
    public void run() {
        sensorDataService.save(ConnectESP8266.getData());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
