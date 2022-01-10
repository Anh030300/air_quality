package com.example.air_quality.config;

public abstract class MqttConfig {

    protected final String broker = "3d31fa520d954a01ada2ce10fb61ce53.s2.eu.hivemq.cloud";
    protected final int qos = 2;
    protected Boolean hasSSL = false; /* By default SSL is disabled */
    protected Integer port = 8883; /* Default port */
    protected final String userName = "springserver";
    protected final String password = "Springserver2394";
    protected final String TCP = "tcp://";
    protected final String SSL = "ssl://";

    /**
     * Custom Configuration
     *
     * @param broker
     * @param port
     * @param ssl
     * @param withUserNamePass
     */
    protected abstract void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass);

    /**
     * Default Configuration
     */
    protected abstract void config();
}