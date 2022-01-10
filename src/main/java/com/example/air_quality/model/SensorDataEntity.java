package com.example.air_quality.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sensor_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorDataEntity extends BaseEntity {
    private String temp;
    private String humi;

}