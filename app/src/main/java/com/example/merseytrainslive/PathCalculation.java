package com.example.merseytrainslive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class PathCalculation {


    private Station from;
    private Station to;
    private static int fromCommonIndex;
    private static int toCommonIndex;
    public PathCalculation(Station from, Station to){
        this.from = from;
        this.to= to;


    }

    public Station[] findRoute(){
        RouteSetup route = new RouteSetup();
        Station [][] routeArray = route.getRoutes();
        boolean fromInRoute = false;
        boolean toInRoute = false;
        int fromIndex = 0;
        int toIndex = 0;
        int toServiceIndex = 0;
        int fromServiceIndex = 0;
        for (int i = 0; i<7; i++){
            for(int j=0; j<routeArray[i].length; j++){
                if(routeArray[i][j].getStationName()==from.getStationName()){

                    fromInRoute = true;
                    fromIndex = j;
                    fromServiceIndex = i;
                }
                if(routeArray[i][j].getStationName()==to.getStationName()){

                    toInRoute = true;
                    toIndex = j;
                    toServiceIndex = i;
                }

            }



            if (fromInRoute == true && toInRoute == true){

            	Station [] outputArray = routeCalculator(fromIndex, fromServiceIndex, toIndex, toServiceIndex, routeArray);
            	return outputArray;


            }else if (fromInRoute == true && toInRoute == false){
            	for (int x = 0; x<7; x++){
                    for(int y=0; y<routeArray[x].length; y++){

                    	if(routeArray[x][y].getStationName()==to.getStationName()){
                            toIndex = y;
                            toServiceIndex = x;
                    	}
                    }  
            	}
            	Set<Station> set = FindCommonElement(routeArray[toServiceIndex],routeArray[fromServiceIndex]);
            	Station [] fromArray = routeCalculator(fromIndex, fromServiceIndex, fromCommonIndex, fromServiceIndex, routeArray);
            	Station [] temptoArray = routeCalculator(toCommonIndex, toServiceIndex, toIndex, toServiceIndex, routeArray);
            	Station[] temptoArray2 = Arrays.copyOfRange(temptoArray, 1, temptoArray.length);
            	Station[]toArray = Arrays.copyOf(temptoArray2, temptoArray2.length + 1);
            	toArray[toArray.length - 1] = routeArray[toServiceIndex][toCommonIndex];
            	int toLength = toArray.length;
            	int fromLength = fromArray.length;

            	Station[] outputArray = new Station[fromLength + toLength];
            	System.arraycopy(fromArray, 0, outputArray, 0, fromLength);
            	System.arraycopy(toArray, 0, outputArray, fromLength, toLength);
            	return outputArray;
            }

            toInRoute = false;
            fromInRoute = false;
        }

        return routeArray[1];
    }
    
    private static Set<Station> FindCommonElement(Station[] arr1, Station[] arr2){
    	Set<Station> set = new HashSet<>();
    	for (int i = 0; i < arr1.length; i++) {
    		for (int j = 0; j < arr2.length; j++) {
    			if (arr1[i].getStationName() == arr2[j].getStationName()) {
    				toCommonIndex = i;
    				fromCommonIndex = j;
    				// add common elements
    				set.add(arr1[i]);
    				break;
    			}
    		}
    	}
    	return set;

    }
    private Station[] routeCalculator(int fromIndex, int fromServiceIndex, int toIndex, int toServiceIndex, Station[][] routeArray){
        if (fromIndex < toIndex) {
            Station[] outputArray = Arrays.copyOfRange(routeArray[fromServiceIndex], fromIndex, toIndex+1);
            return outputArray;
        } else {
            Station[] outputArray = Arrays.copyOfRange(routeArray[fromServiceIndex], toIndex, fromIndex+1);
            
            Station[] temparray = new Station[outputArray.length];
            int counter = 0;
            for (int x = outputArray.length-1; x >= 0; x--){

            	temparray[x] = outputArray[counter];
                counter++;
                
            }
            outputArray = temparray;

            return outputArray;
        }
    }
    
}
