package com.sphy.stetic.presenter;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.ClientEditContract;
import com.sphy.stetic.model.ClientEditModel;

public class ClientEditPresenter implements ClientEditContract.Presenter {
    private ClientEditContract.View view;
    private ClientEditContract.Model model;

    public ClientEditPresenter(ClientEditContract.View view) {
        this.view = view;
        this.model = new ClientEditModel();
    }

    @Override
    public void updateClient(Client client) {
        model.updateClient(client, new ClientEditModel.OnUpdateClientListener() {
            @Override
            public void onUpdateClientSuccess() {
                view.showUpdateSuccessMessage();
            }

            @Override
            public void onUpdateClientError(String message) {
                // Manejar el error, por ejemplo, mostrar un mensaje de error en la vista
            }
        });
    }

    @Override
    public void cancelEditing() {
        view.showCancelMessage();
    }
}
