package com.sphy.stetic.Db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Domain.Shop;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract ProductDao productDao();

}


