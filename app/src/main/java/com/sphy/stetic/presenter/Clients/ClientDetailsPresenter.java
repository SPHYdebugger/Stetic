package com.sphy.stetic.presenter.Clients;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.model.Clients.ClientDetailsModel;

public class ClientDetailsPresenter implements ClientDetailsContract.Presenter {

    private ClientDetailsContract.View view;
    private ClientDetailsContract.Model model;

    public ClientDetailsPresenter(ClientDetailsContract.View view) {
        this.view = view;
        this.model = new ClientDetailsModel(((AppCompatActivity) view).getApplicationContext());
    }

    @Override
    public void getClientDetails(String dni) {
        model.getClientDetails(dni, new ClientDetailsModel.OnClientDetailsListener() {
            @Override
            public void onClientDetailsSuccess(Client client) {
                view.displayClientDetails(client);
            }

            @Override
            public void onClientDetailsError(String message) {

            }
        });
    }

    @Override
    public void updateClient(Client client) {
        model.updateClient(client, new ClientDetailsModel.OnUpdateListener() {
            @Override
            public void onUpdateSuccess() {
                view.showUpdateSuccessMessage();
            }

            @Override
            public void onUpdateError(String message) {
                view.showUpdateErrorMessage();
            }
        });
    }

    @Override
    public void deleteClient(String dni) {
        model.deleteClient(dni, new ClientDetailsModel.OnDeleteListener() {
            @Override
            public void onDeleteSuccess() {
                view.showDeleteSuccessMessage();
            }

            @Override
            public void onDeleteError(String message) {
                view.showDeleteErrorMessage();
            }
        });
    }
}
