package com.example.air_quality.connectESP;

import com.example.air_quality.model.SensorData;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Component
public class ConnectESP8266 {

    public SensorData getData() throws IOException {

        ServerSocket serverSocket;
        Socket socket;
        Scanner input;
        String msg1,temp,humi;

        serverSocket = new ServerSocket(3000);
        socket = serverSocket.accept();
        System.out.println("Connection ok \n");
        input = new Scanner(socket.getInputStream());
        msg1 = input.nextLine();
        System.out.println("Temp");
        System.out.println(msg1);
        temp = msg1;
        msg1 = input.nextLine();
        System.out.println("Humi");
        humi = msg1;
        System.out.println(msg1);

        socket.close();
        serverSocket.close();
        return new SensorData(temp,humi);
    }
}