package com.example.air_quality.mqtt.config;

public abstract class MqttConfig {
    protected String username = "springserver";
    protected String password = "Springserver2394";
    protected String broker = "3d31fa520d954a01ada2ce10fb61ce53.s2.eu.hivemq.cloud";
    protected Integer port= 8883;
    protected int timeout;
    protected int keepAlive;
    protected final String TCP = "tcp://";
    protected final String SSL = "ssl://";
    protected final boolean hasSSL = true;
    protected final int qos = 2;

    protected abstract void connect(String broker, Integer port,Boolean ssl, Boolean withUserNamePass);

    /**
     * Default Configuration
     */
    protected abstract void connect();
}
