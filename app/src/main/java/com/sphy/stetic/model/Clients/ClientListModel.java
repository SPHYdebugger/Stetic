package com.sphy.stetic.model.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientListContract;

import java.util.List;

public class ClientListModel implements ClientListContract.Model {

    private Context context;

    public ClientListModel(Context context){
        this.context = context;
    }

    @Override
    public void loadAllClients(OnLoadClientsListener listener) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        List<Client> clients = db.clientDao().getAll();
        // Si va bien
        listener.onLoadClientsSuccess(clients);

    }
}
