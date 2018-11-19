package com.example.nasser.weather;

import java.sql.Date;

public class Temperature {
    private Date date;
    private double value;

    public Temperature() {
    }

    public Temperature(Date date, double value) {

        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }




}
