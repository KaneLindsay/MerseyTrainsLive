package com.example.merseytrainslive.livedata;

public class Service {
    private String serviceCode;
    private String trainID;
    private String dateCreated;
    private String currentLocation;
    private int delay;
    private boolean atStation;

    public Service(String[] activationMessage ) {   //Constructor is given the activationMessage from the API
        serviceCode = activationMessage[0];
        trainID = activationMessage[1];
        dateCreated = activationMessage[2];
        currentLocation = activationMessage[3];
        atStation = false;
    }

    public void trainMovement(String[] movementMessage) {   //Method is given the movementMessage from the API
        if (movementMessage[2] == "ARRIVAL") {
            currentLocation = movementMessage[4];
            atStation = true;
        }  else {// if (movementMessage[2] == "DEPARTURE")
            atStation = false;
        }

        //ScheduleManager.addDelay(trainID, delay);
    }

    //Find station linked to STANOX, place train in the station or between this and the next station, depending on atStation

    //Getters for private variables
    public String getDateCreated() {
        return dateCreated;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public String getServiceCode() {
        return serviceCode;
    }
    public String getTrainID() {
        return trainID;
    }

    //NEED PROCESSING OF TRAIN MOVEMENTS, CANCELLATION WILL BE HANDLED BY THE MANAGER
}
