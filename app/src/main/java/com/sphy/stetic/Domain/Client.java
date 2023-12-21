package com.sphy.stetic.Domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Client {

    @PrimaryKey
    private @NonNull String firstName;
    @ColumnInfo
    private String lastName;
    @ColumnInfo
    private String dni;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String city;
    @ColumnInfo
    private String birthDay;

    @ColumnInfo
    private boolean vip;

    public Client(){}

    public Client(@NonNull String firstName, String lastName, String dni, String address, String city, String birthDay, boolean vip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.address = address;
        this.city = city;
        this.birthDay = birthDay;
        this.vip = vip;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
