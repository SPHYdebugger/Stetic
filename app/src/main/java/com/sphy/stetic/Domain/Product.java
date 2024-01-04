package com.sphy.stetic.Domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Product {

    @PrimaryKey
    private @NonNull long id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int size;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private String registrationDate;
    @ColumnInfo
    private boolean dangerous;



    public Product(){}

    public Product(@NonNull long id, String name, int size, String description, float price, String registrationDate, boolean dangerous) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.description = description;
        this.price = price;
        this.registrationDate = registrationDate;
        this.dangerous = dangerous;

    }

    public Product(String name, int size, String description, float price, boolean dangerous) {

        this.name = name;
        this.size = size;
        this.description = description;
        this.price = price;
        this.dangerous = dangerous;
    }


    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }


}