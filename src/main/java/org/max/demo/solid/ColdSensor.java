package org.max.demo.solid;

public class ColdSensor extends TemperatureSensor{

    public ColdSensor(int valueMax) {
        super(valueMax);
    }

    public Boolean isAlarm() {
        return null;
    }
}
