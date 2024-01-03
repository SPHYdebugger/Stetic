package com.sphy.stetic.Domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class Shop {

    @PrimaryKey
    @NonNull
    private long id;
    @ColumnInfo
    private @NonNull String name;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String city;

    @ColumnInfo
    private boolean solarium;

    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;

    public Shop(){}

    public Shop(long id, @NonNull String name, String address, String city, boolean solarium, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.solarium = solarium;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Shop(@NonNull String name, String address, String city, boolean solarium, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.solarium = solarium;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isSolarium() {
        return solarium;
    }

    public void setSolarium(boolean solarium) {
        this.solarium = solarium;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}