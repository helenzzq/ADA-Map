package com.example.uoft_map;

import java.util.List;

public class Loc {
    private double longitude;
    private double latitude;
    private String fullName;
    private String absName;
    private String address;

    public Loc(double latitude, double longitude, String fullName, String absName, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.fullName = fullName;
        this.absName = absName;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbsName() {
        return absName;
    }

    public void setAbsName(String absName) {
        this.absName = absName;
    }



}
