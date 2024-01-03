package com.sphy.stetic.presenter.Clients;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientListContract;
import com.sphy.stetic.contract.Products.ProductListContract;
import com.sphy.stetic.model.Clients.ClientListModel;
import com.sphy.stetic.model.Products.ProductListModel;
import com.sphy.stetic.view.Clients.ClientListView;
import com.sphy.stetic.view.Products.ProductListView;

import java.util.List;

public class ClientListPresenter implements ClientListContract.Presenter, ClientListContract.Model.OnLoadClientsListener{

    private ClientListView view;
    private ClientListModel model;

    public ClientListPresenter(ClientListView view){
        this.view = view;
        model= new ClientListModel(view);
    }

    @Override
    public void loadAllClients() {
        model.loadAllClients(this);
    }



    @Override
    public void onLoadClientsSuccess(List<Client> clients) {
        view.listClients(clients);
    }

    @Override
    public void onLoadClientsError(String message) {
        view.showMessage(message);
    }
}
