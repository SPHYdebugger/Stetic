package com.sphy.stetic.model.Products;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientSearchContract;

import java.util.List;

public class ProductSearchModel implements ClientSearchContract.Model {
    private AppDatabase database;

    public ProductSearchModel(AppDatabase database) {
        this.database = database;
    }

    @Override
    public List<Client> getAllClients() {
        return database.clientDao().getAll();
    }
}