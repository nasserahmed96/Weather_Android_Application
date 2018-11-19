package com.example.nasser.weather;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

public class TemperatureValues {
    private static final TemperatureValues ourInstance = new TemperatureValues();
    private ArrayList<Temperature> mTemperature;

    public static TemperatureValues getInstance() {
        return ourInstance;
    }

    private TemperatureValues() {
        mTemperature = new ArrayList<Temperature>();
    }

    public ArrayList<Temperature> getTemperature(){
        return mTemperature;
    }

}
