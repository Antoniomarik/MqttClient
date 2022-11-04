package org.example.mqqt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Broker {
    private  String brokerURl;
    private  String clientID;
    private final static int qos = 2;
    private final static MemoryPersistence mp = new MemoryPersistence();
    private  List<Sensor> sensors = new ArrayList<>();


    public Broker(){
    }
    public Broker(String url, String cID){//, List<Sensor> sensors
        this.brokerURl = url;
        this.clientID = cID;
        //this.sensors = sensors;
    }

    MqttClient firstClient = null;
    public MqttClient Connect () {
        try {
            firstClient = new MqttClient(brokerURl, clientID, mp);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            System.out.println("Connecting to broker: " + brokerURl);
            firstClient.connect(connectOptions);
            System.out.println("Connected! ");

        } catch (MqttException msg) {
            msg.getLocalizedMessage();
        }
        return firstClient;
    }

    public  void SendMsg(MqttClient cl1){
        sensors.forEach((sensor ->{
        try{
            MqttMessage msg = new MqttMessage((sensor.getNameOF()+ "\n"+
                                                "Final Result: " + sensor.getFinalResult()+"\n"+
                                                "Faktor: "+sensor.getFactor()+"\n"+
                                                "Raspon: "+sensor.getMin() +" to "+sensor.getMax()+"\n"+
                                                "Mjerna Jedinica: " +sensor.getUnit() + "\n")  .getBytes());
            msg.setQos(qos);
            cl1.publish("topic",msg);
            System.out.println("Msg is published!");
        }
        catch (MqttException ms){
            ms.getLocalizedMessage();
        }}));
    }

    public static Broker deserialization(String path){
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        try {

            return mapper.readValue(new File(path), Broker.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getBrokerURl (){
        return this.brokerURl;
    }
    public String getClientID(){
        return this.clientID;
    }
    public void addSensor(Sensor sensor){
        sensors.add(sensor);
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }
}



