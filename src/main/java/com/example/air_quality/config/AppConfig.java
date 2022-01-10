package com.example.air_quality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

//@Configuration
//public class AppConfig {
//
//    @Bean("simpleAsync")
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
//}

@Configuration
public class AppConfig {

    @Bean("SimpleAsync")
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
