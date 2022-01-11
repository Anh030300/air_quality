package com.example.air_quality.connectESP;

import com.example.air_quality.model.SensorDataEntity;
import com.example.air_quality.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Component
public class ConnectESP8266 {

    public static SensorDataEntity getData() throws IOException {
        ServerSocket serverSocket;
        Socket socket;
        Scanner input;
        String msg1,temp,humidity;

        serverSocket = new ServerSocket(3000);
        socket = serverSocket.accept();
        System.out.println("Connection ok \n");
        input = new Scanner(socket.getInputStream());
        msg1 = input.nextLine();
        System.out.println("Temp");
        System.out.println(msg1);
        temp = msg1;
        msg1 = input.nextLine();
        System.out.println("Humidity");
        humidity = msg1;
        System.out.println(msg1);
        socket.close();
        serverSocket.close();
        return new SensorDataEntity(temp,humidity);
    }
}