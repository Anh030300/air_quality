package com.example.air_quality.repository;

import com.example.air_quality.model.SensorDataEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorDataRepository extends BaseRepository<SensorDataEntity,Long>{
    //@Query("SELECT s FROM SensorDataEntity s ORDER BY s.id DESC LIMIT 10")
    List<SensorDataEntity> findTop10ByOrderByCreatedTimeDesc();
    List<SensorDataEntity> findTop1ByOrderByCreatedTimeDesc();
}
