package com.sphy.stetic.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Shop;

import java.util.List;

@Dao
public interface ShopDao {
    @Query("SELECT * FROM Shop")
    List<Shop> getAll();

    @Query("SELECT * FROM Shop WHERE name = :name")
    Shop findByName(String name);

    @Insert
    void insert(Shop shop);

    @Update
    void update(Shop shop);

    @Delete
    void delete(Shop shop);

    @Query("DELETE FROM Shop WHERE name = :name")
    void deleteByName(String name);
}
