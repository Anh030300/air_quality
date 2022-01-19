package com.example.air_quality.service;

import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public SensorDataEntity findTheNewest(){
        return sensorDataRepository.findTheNewest();
    }
}
