package com.sphy.stetic.presenter.Clients;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.model.Clients.ClientRegisterModel;
import com.sphy.stetic.view.Clients.RegisterClientView;

public class ClientRegisterPresenter implements ClientRegisterContract.Presenter {

    private RegisterClientView view;
    private ClientRegisterModel model;

    public ClientRegisterPresenter(RegisterClientView view) {
        this.view = view;
        model = new ClientRegisterModel(view);
    }

    @Override
    public void insertClient(Client client) {
        model.insertClient(client, new ClientRegisterModel.OnClientInsertedListener() {
            @Override
            public void onClientInsertedSuccess() {
                view.showInsertSuccessMessage();
                view.clearFields();
            }

            @Override
            public void onClientInsertedError(String message) {
                view.showInsertErrorMessage();
            }
        });
    }
}
