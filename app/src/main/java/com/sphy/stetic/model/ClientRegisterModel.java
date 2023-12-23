package com.sphy.stetic.model;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.ClientRegisterContract;

public class ClientRegisterModel implements ClientRegisterContract.Model {

    private Context context;

    public ClientRegisterModel(Context context) {
        this.context = context;
    }



    @Override
    public void insertClient(Client client, OnClientInsertedListener listener) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.clientDao().insert(client);
        listener.onClientInsertedSuccess();
    }
}
