package org.example.gui;

import org.example.mqqt.Sensor;
import org.example.mqqt.Broker;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Broker firstClient =  new Broker("tcp://127.0.0.1:1883","User1");//new ArrayList<>()

        Sensor msg = new Sensor("Test1",10,-221,1222.3, "CELZIUS");
        Sensor msg2 = new Sensor("Test2",1,1.3,1222.3, "CELZIUS", 1);

        firstClient.addSensor(msg);
        firstClient.addSensor(msg2);

        String path = "C:\\Users\\38595\\Desktop\\jsonfile.json\\";

        Broker cl2 = Broker.deserialization(path);
        cl2.SendMsg(cl2.Connect());

    }
}
