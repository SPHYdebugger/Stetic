package com.sphy.stetic.model.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;

public class ClientDetailsModel implements ClientDetailsContract.Model {

    private Context context;

    public ClientDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void getClientDetails(String dni, OnClientDetailsListener listener) {

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Client client = db.clientDao().findByDni(dni);

        if (client != null) {
            listener.onClientDetailsSuccess(client);
        } else {
            listener.onClientDetailsError("Cliente no encontrado");
        }
    }

    @Override
    public void updateClient(Client client, OnUpdateListener listener) {

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        try {
            db.clientDao().update(client);
            listener.onUpdateSuccess();
        } catch (Exception e) {
            listener.onUpdateError("Error al actualizar el cliente");
        }
    }

    @Override
    public void deleteClient(String dni, ClientDetailsContract.Model.OnDeleteListener listener) {

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        try {
            db.clientDao().deleteByDni(dni);
            listener.onDeleteSuccess();
        } catch (Exception e) {
            listener.onDeleteError("Error al eliminar el cliente");
        }
    }
}
