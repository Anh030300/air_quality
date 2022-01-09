package com.example.air_quality.repository;


import com.example.air_quality.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<T extends BaseEntity, ID> extends CrudRepository<T, ID> {
}
