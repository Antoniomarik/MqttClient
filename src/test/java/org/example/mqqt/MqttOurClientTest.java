package org.example.mqqt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;


import static org.junit.Assert.*;

public class MqttOurClientTest {

    @Test
    public void connect() {
        //tcp://127.0.0.1:1883
        final MemoryPersistence mp = new MemoryPersistence();

        try {
            Broker firstClient =  new Broker("","User1");
            firstClient.Connect();

        }catch(IllegalArgumentException ex){
            assertEquals("missing scheme in broker URI: ", ex.getMessage());
        }


    }

    @Test
   public void sendMsg()  {
        Broker firstClient =  new Broker("tcp://127.0.0.1:1883","User1");
        Sensor msg1 = new Sensor("Test did fail;",10,1.3,1222.3, "CELZIUS");

        try{
            MqttMessage msg = new MqttMessage((msg1.getName()+ "\n"+
                    "Final Result: " + msg1.getFinalResult()+"\n"+
                    "Faktor: "+msg1.getFactor()+"\n"+
                    "Raspon: "+msg1.getMin() +" to "+msg1.getMax()+"\n"+
                    "Mjerna Jedinica: " +msg1.getUnit() + "\n")  .getBytes());
            msg.setQos(2);
            (firstClient.Connect()).publish("test",msg);
        }
        catch (MqttException ms){
            ms.getMessage();

        }
        assertEquals("Test Should Fail",msg1.getName());
    }
}