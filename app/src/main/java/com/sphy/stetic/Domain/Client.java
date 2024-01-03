package com.sphy.stetic.Domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Client {

    @PrimaryKey
    private @NonNull long id;
    @ColumnInfo
    private @NonNull String firstname;
    @ColumnInfo
    private String lastname;
    @ColumnInfo
    private @NonNull String dni;
    @ColumnInfo
    private String city;
    @ColumnInfo
    private boolean vip;

    public Client(){}

    public Client(long id, @NonNull String firstName, String lastName, @NonNull String dni, String city, boolean vip) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.dni = dni;
        this.city = city;
        this.vip = vip;
    }

    public Client(@NonNull String firstName, String lastName, @NonNull String dni, String city, boolean vip) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.dni = dni;
        this.city = city;
        this.vip = vip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @NonNull
    public String getDni() {
        return dni;
    }

    public void setDni(@NonNull String dni) {
        this.dni = dni;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
