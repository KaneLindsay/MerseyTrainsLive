package com.example.merseytrainslive;

import java.util.Arrays;
        import java.util.List;

public class Station {
    private String stationName;
    private String stanox;
    private int platforms;
    private String tiploc;


    //Constructor
    public Station(String s, String stan, int plat, String tip) {
        stationName = s;
        stanox = stan;
        platforms = plat;
        tiploc = tip;

        //Example station definition:

    }

    //getters for private variables
    public String getStanox() { return stanox; }
    public String getStationName() { return stationName; }
    public String getTipLoc() { return tiploc; }
}