package mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MqttPubSubConfig extends MqttConfig implements MqttCallback {
    protected String brokerUrl = null;
    final protected String colon = ":";
    protected String clientId;

    protected MqttClient mqttClient = null;
    protected MqttConnectOptions connectionOptions = null;
    protected MemoryPersistence persistence = null;

    protected static final Logger logger = LoggerFactory.getLogger(MqttPubSubConfig.class);

    public MqttPubSubConfig(){
        this.clientId ="defaultClient";
    }

    @Override
    protected void connect(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        String protocol = this.TCP;
        if (ssl) {
            protocol = this.SSL;
        }

        this.brokerUrl = protocol + this.broker + colon + port; logger.info("Connecting to "+this.brokerUrl);
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(false);
            if (withUserNamePass) {
                if (password != null) {
                    this.connectionOptions.setPassword(this.password.toCharArray());
                }
                if (username != null) {
                    this.connectionOptions.setUserName(this.username);
                }
            }
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }
    }
}
