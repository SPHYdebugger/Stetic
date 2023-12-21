package com.sphy.stetic.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sphy.stetic.Domain.Client;

import java.util.List;

@Dao
public interface ClientDao {
    @Query("SELECT * FROM Client")
    List<Client> getAll();

    @Query("SELECT * FROM Client WHERE firstName = :name")
    List<Client> findByName(String name);

    @Insert
    void insert(Client client);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);
}
