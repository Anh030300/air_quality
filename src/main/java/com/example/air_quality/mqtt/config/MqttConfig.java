package com.example.air_quality.mqtt.config;

public abstract class MqttConfig {
    protected String username = "springserver";
    protected String password = "Springserver2394";
    protected String broker = "broker.hivemq.com";
    protected Integer port= 1883;
    protected int timeout;
    protected int keepAlive;
    protected final String TCP = "tcp://";
    protected final String SSL = "ssl://";
    protected final boolean hasSSL = false;
    protected final boolean withUserNamePass = true;
    protected final int qos = 2;

    protected abstract void connect(String broker, Integer port,Boolean ssl, Boolean withUserNamePass);

    /**
     * Default Configuration
     */
    protected abstract void connect();
}
