package com.example.air_quality.service;

import com.example.air_quality.model.BaseEntity;
import com.example.air_quality.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BaseService<T extends BaseEntity> {

    @Autowired
    BaseRepository<T,Long> baseRepository;

    public T findById(Long id){
        return baseRepository.findById(id).orElse(null);
    }

    public List<T> findAll(){
        return (List<T>) baseRepository.findAll();
    }

    public boolean save(T t){
        baseRepository.save(t);
        return true;
    }

}