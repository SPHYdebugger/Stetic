package com.sphy.stetic.model.Clients;

import android.content.Context;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.api.ClientApi;
import com.sphy.stetic.api.ClientApiInterface;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Clients.ClientEditContract;
import com.sphy.stetic.contract.Products.ProductDetailsContract;
import com.sphy.stetic.contract.Products.ProductEditContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientEditModel implements ClientEditContract.Model {

    private Context context;

    public ClientEditModel(Context context) {
        this.context = context;
    }


    @Override
    public void updateClient(long id, Client client, ClientEditContract.Model.OnUpdateClientListener listener) {
        ClientApiInterface api = ClientApi.buildInstance();
        Call<Client> editClientCall = api.editClientById(id, client);
        editClientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    listener.onUpdateClientSuccess("Cliente modificado");
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                listener.onUpdateClientError("Cliente modificado");
            }
        });
    }

    @Override
    public void getClientDetails(long id, OnClientDetailsListener listener) {

        ClientApiInterface api = ClientApi.buildInstance();
        Call<Client> getClientCall = api.getClientById(id);
        getClientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    listener.onClientDetailsSuccess(response.body());
                } else {
                    listener.onClientDetailsError("Error al obtener los detalles del cliente");
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                listener.onClientDetailsError("Error de conexión al obtener los detalles del cliente");
            }
        });
    }
}
