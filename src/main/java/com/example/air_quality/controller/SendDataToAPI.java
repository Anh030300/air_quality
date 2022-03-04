package com.example.air_quality.controller;

import com.example.air_quality.service.SensorDataService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class SendDataToAPI {
    @Autowired
    SensorDataService sensorDataService;
    String url = "http://9194-34-147-46-205.ngrok.io/publish";
    private static HttpURLConnection setupConnection(String url) throws IOException {

        URL line_api_url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        return conn;
    }
   // @Scheduled(fixedRate = 1800000)
    @Scheduled(cron = "0 0,30 * * * ?")
    public void scheduleTask() throws IOException {
        HttpURLConnection conn = setupConnection(url);
        JsonObject json = sensorDataService.senDataToApi();
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(json.toString());
        System.out.println(json.toString());
        writer.close();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
