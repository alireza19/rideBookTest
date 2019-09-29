package com.example.ridebooktest;

import java.util.ArrayList;

// ride manager handles 1 ride object at a time in a temporary array
// acts as a medium of exchange when retrieving information
public class RideManager {

    private static ArrayList rideDataList = new ArrayList<>();

    public static void giveRide(Ride ride){
        rideDataList.add(ride);
    }

    public static Ride getRide(){

    }
}
