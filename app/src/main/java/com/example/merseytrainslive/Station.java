package com.example.merseytrainslive;

public class Station {
    private String stationName;
    private int trainCount = 0;

    Station(String stationName) {
        this.stationName = stationName;
    }

    public int getTrainCount() {
        return trainCount;
    }

    public String getStationName() {
        return stationName;
    }

    public void incrementTrainCount() {
        trainCount++;
    }

    public void decrementTrainCount() {
        if (trainCount == 0) {
            throw new RuntimeException("Number of trains in station cannot be negative.");
        } else {
            trainCount--;
        }
    }

}
