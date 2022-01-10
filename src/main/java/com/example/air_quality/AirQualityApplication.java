package com.example.air_quality;

import com.example.air_quality.mqtt.MessageListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class AirQualityApplication{

    public static void main(String[] args) {
        SpringApplication.run(AirQualityApplication.class, args);
    }

    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("SimpleAsync") TaskExecutor executor){
        return args -> executor.execute(new MessageListener());
    }
}


//@SpringBootApplication
//public class AirQualityApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(AirQualityApplication.class, args);
//    }
//
//}