package org.example.mqqt;
import java.util.Random;

public class Sensor {
    private String nameOF;
    private double finalResult;
    private int factor;
    private String unit;
    private double min;
    private double max;

    public Sensor(){
    }
    public Sensor(String name, int Factor, double min, double max, String unit ){
        this.nameOF = name;
        this.finalResult = 0;
        this.factor = Factor;
        double f = factor;
        this.unit = unit;
        this.min = min;
        this.max = max;

        Random random = new Random();

        this.finalResult = random.nextDouble(max + min) - min;
    }
    public Sensor(String name, int Factor, double min, double max, String unit, int Days ){
        this.nameOF = name;
        this.finalResult = 0;
        this.factor = Factor;
        double f = factor;
        this.unit = unit;
        this.min = min;
        this.max = max;

        Random random = new Random();
        double number = random.nextDouble(max + min) - min;

        this.finalResult = number*Days;
    }
    public String getNameOF() {
        return nameOF;
    }
    public double getFinalResult(){

        return this.finalResult;
    }
    public int getFactor(){

        return this.factor;
    }
    public String getUnit(){

        return this.unit;
    }
    public double getMin(){

        return this.min;
    }
    public double getMax(){

        return this.max;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
