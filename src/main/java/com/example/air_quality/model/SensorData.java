package com.example.air_quality.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
public class SensorData {
    private String temp;
    private String humi;
}
