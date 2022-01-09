package com.example.air_quality;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.task.TaskExecutor;

//@SpringBootApplication
//public class AirQualityApplication extends SpringBootServletInitializer {
//
//    @Autowired
//    Runnable MessageListener;
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(AirQualityApplication.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(AirQualityApplication.class, args);
//    }
//
//    @Bean
//    public CommandLineRunner schedulingRunner(TaskExecutor executor){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                executor.execute(MessageListener);
//            }
//        };
//    }
//}


@SpringBootApplication
public class AirQualityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirQualityApplication.class, args);
    }

}