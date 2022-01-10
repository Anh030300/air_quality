package com.example.air_quality.repository;

import com.example.air_quality.model.SensorDataEntity;
import org.springframework.data.jpa.repository.Query;

public interface SensorDataRepository extends BaseRepository<SensorDataEntity,Long>{
    @Query("SELECT s FROM SensorDataEntity s WHERE s.id=(SELECT max(id) FROM SensorDataEntity)")
    public SensorDataEntity findTheNewest();
}
