package com.example.merseytrainslive;
import java.util.Arrays;

public class Station {
    private String stationName;
    private String stanox;
    private int platforms;
    private String tiploc;

    public Station(String s, String stan, int plat, String tip) {
        stationName = s;
        stanox = stan;
        platforms = plat;
        tiploc = tip;

    }

    public int getPlatforms() {
        return platforms;
    }

    public String getStanox() {
        return stanox;
    }

    public String getStationName() {
        return stationName;
    }

    public String getTiploc() {
        return tiploc;
    }
    //Example station definition:
        //Station lol = new Station("Liverpool Lime Street", "stanox", 8, "tiploc");


    static Station[] addAllStations(Station[] add, Station[] allStations, int currentIndex) {
        for (Station spr : add) {
            if (!Arrays.asList(allStations).contains(spr)) {
                allStations[currentIndex] = spr;
                currentIndex++;
            }
        }
        return allStations;
    }

    public static void defineRoutes() {

    }

}
