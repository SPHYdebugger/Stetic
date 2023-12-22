package com.sphy.stetic.Domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class Shop {

    @PrimaryKey
    private @NonNull String name;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String city;

    @ColumnInfo
    private boolean solarium;

    public Shop(){}

    public Shop(@NonNull String name, String address, String city, boolean solarium) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.solarium = solarium;
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
}