package com.sachi.amonic.utils;

public class AirportDataClass {
    public String id;
    public String airport;

    public AirportDataClass(String id, String airport) {
        this.id = id;
        this.airport = airport;
    }

    @Override
    public String toString() {
        return airport;
    }
}
