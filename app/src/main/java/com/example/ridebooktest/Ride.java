package com.example.ridebooktest;

import java.io.Serializable;
import java.util.Date;

public class Ride implements Serializable {
    private String date;
    private String time;
    private float distance;
    private float speed;
    private int cadence;
    private String comment;

    public Ride(String date, String time, float distance, float speed, int cadence, String comment) {
        this.date = date;
        this.time = time;
        this.distance = distance;
        this.speed = speed;
        this.cadence = cadence;
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
