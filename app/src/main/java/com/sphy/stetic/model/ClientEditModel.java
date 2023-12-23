package com.sphy.stetic.model;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.ClientEditContract;

public class ClientEditModel implements ClientEditContract.Model {
    @Override
    public void updateClient(Client client, OnUpdateClientListener listener) {
        // Lógica para actualizar el cliente en la base de datos
        // ...

        // Ejemplo: Aquí deberías llamar al método onUpdateClientSuccess() si la operación es exitosa,
        // o onUpdateClientError(message) si hay un error.
    }
}
