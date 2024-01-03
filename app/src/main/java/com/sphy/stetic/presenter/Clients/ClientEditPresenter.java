package com.sphy.stetic.presenter.Clients;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientEditContract;
import com.sphy.stetic.contract.Products.ProductEditContract;
import com.sphy.stetic.model.Clients.ClientEditModel;
import com.sphy.stetic.model.Products.ProductEditModel;
import com.sphy.stetic.view.Clients.ClientEditView;
import com.sphy.stetic.view.Products.ProductEditView;

public class ClientEditPresenter implements ClientEditContract.Presenter {

    private final ClientEditView view;
    private final ClientEditModel model;

    public ClientEditPresenter(ClientEditView view) {
        this.view = view;
        this.model = new ClientEditModel(view);
    }

    @Override
    public void getClientDetails(long clientId) {
        model.getClientDetails(clientId, new ClientEditContract.Model.OnClientDetailsListener() {
            @Override
            public void onClientDetailsSuccess(Client client) {
                view.displayClientDetails(client);
            }
            @Override
            public void onClientDetailsError(String errorMessage) {

            }
        });
    }

    @Override
    public void updateClient(long id, Client client) {
        model.updateClient(id, client, new ClientEditContract.Model.OnUpdateClientListener() {

            @Override
            public void onUpdateClientSuccess(String successMessage) {
                view.showUpdateErrorMessage();
            }
            @Override
            public void onUpdateClientError(String errorMessage) {
                view.showUpdateErrorMessage();
            }
        });
    }
}
