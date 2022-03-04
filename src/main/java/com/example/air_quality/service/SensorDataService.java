package com.example.air_quality.service;

import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.repository.SensorDataRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SensorDataService extends BaseService<SensorDataEntity>{
    @Autowired
    SensorDataRepository sensorDataRepository;

    public boolean save(SensorDataEntity sensorDataEntity) {
        if(sensorDataEntity==null){
            return false;
        }
        sensorDataEntity.setCreatedTime(new Date());
        sensorDataRepository.save(sensorDataEntity);
        return true;
    }

    public List<SensorDataEntity> findTop10(){
        return sensorDataRepository.findTop10ByOrderByCreatedTimeDesc();
    }
    public List<SensorDataEntity> findTop1(){
        return sensorDataRepository.findTop1ByOrderByCreatedTimeDesc();
    }
    public JsonObject senDataToApi()
    {
        JsonObject json = new JsonObject();
        SensorDataEntity sensorDataEntity= sensorDataRepository.findTop1ByOrderByCreatedTimeDesc().get(0);
        json.addProperty("time",sensorDataEntity.convertDate());
        json.addProperty("temp",Double.parseDouble(sensorDataEntity.getTemp()));
        json.addProperty("humidity",Double.parseDouble(sensorDataEntity.getHumidity()));
        return json;
    }
}
