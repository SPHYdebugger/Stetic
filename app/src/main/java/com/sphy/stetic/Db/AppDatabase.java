package com.sphy.stetic.Db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sphy.stetic.Domain.Client;

@Database(entities = {Client.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientDao clientDao();
}
