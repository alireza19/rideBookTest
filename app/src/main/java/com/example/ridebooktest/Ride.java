package com.example.ridebooktest;

import java.io.Serializable;
import java.util.Date;

public class Ride implements Serializable {
    private String date;

    public Ride(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
