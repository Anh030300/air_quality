package com.example.air_quality;

import com.example.air_quality.mqtt.SensorSubscriber;
import com.example.air_quality.service.SensorDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AirQualityApplication{

    public static void main(String[] args) {
        //GetDataThread thread = new GetDataThread();

        ApplicationContext context =SpringApplication.run(AirQualityApplication.class, args);
        SensorSubscriber sensorSubscriber = new SensorSubscriber(context.getBean(SensorDataService.class));
        sensorSubscriber.subscribeMessage("sensor");
        //thread.run();
    }

//    @Bean
//    public CommandLineRunner schedulingRunner(@Qualifier("SimpleAsync") TaskExecutor executor){
//        return args -> executor.execute(new MessageListener());
//    }
}