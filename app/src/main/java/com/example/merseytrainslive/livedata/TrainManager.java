package com.example.merseytrainslive.livedata;

import java.util.Vector;

public class TrainManager {
    private Vector<Service> services;

    public static void main(String[] args) {
        TrainManager manager = new TrainManager();
        Consumer movementsHandler = new Consumer(manager);
    }

    public void moveService(String[] movementMessage) {
        System.out.println("Movement message received");
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getTrainID() == movementMessage[1]) {
                services.get(i).trainMovement(movementMessage);
            }
        }

        //movementMessage[3] contains no. of mins delayed by
        //Need sql statement to update train times with delayed time

    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void activateService(String[] activationMessage) {   //Given activationMessage from movements API
        System.out.println("Activation message received");
        Service newService = new Service(activationMessage);    //Create new service using activationMessage
        services.add(newService);   //Add new service into services
    }

    public void cancelService(String[] cancellationMessage) {   //Given cancellationMessage from movements API
        System.out.println("Cancellation message received");
        for (int i = 0; i < services.size(); i++) { //Search through services
            if (services.get(i).getServiceCode() == cancellationMessage[0]) {   //If train at services[i] is the service to be cancelled
                services.remove(i); //Remove from services
            }
        }
    }

}
