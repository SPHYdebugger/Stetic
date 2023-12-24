package com.sphy.stetic.model;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.ClientSearchContract;

import java.util.List;

public class ClientSearchModel implements ClientSearchContract.Model {
    private AppDatabase database;

    public ClientSearchModel(AppDatabase database) {
        this.database = database;
    }

    @Override
    public List<Client> getAllClients() {
        return database.clientDao().getAll();
    }
}
