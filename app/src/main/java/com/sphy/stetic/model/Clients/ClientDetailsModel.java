package com.sphy.stetic.model.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Db.ProductDao;
import com.sphy.stetic.api.ClientApi;
import com.sphy.stetic.api.ClientApiInterface;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.contract.Products.ProductDetailsContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientDetailsModel implements ClientDetailsContract.Model {

    private Context context;

    public ClientDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void getClientDetails(long id, OnClientDetailsListener listener) {

        ClientApiInterface api = ClientApi.buildInstance();
        Call<Client> getClientCall = api.getClientById(id);
        getClientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Log.e("getClient", response.message());
                Client client = response.body();
                listener.onClientDetailsSuccess(client);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("getClient", t.getMessage());
                listener.onClientDetailsError("Se ha producido un error al conectar con el servidor");
            }
        });

    }


    @Override
    public void deleteClient(String dni, OnDeleteListener listener) {

        ClientApiInterface api = ClientApi.buildInstance();
        Call<Void> deleteClientCall = api.deleteClient(dni);
        deleteClientCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onDeleteSuccess();
                } else {
                    Log.e("deleteClient", "Error al eliminar el cliente: " + response.message());
                    listener.onDeleteError("Error al eliminar el cliente");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("deleteClient", "Error al conectar con el servidor: " + t.getMessage());
                listener.onDeleteError("Se ha producido un error al conectar con el servidor");
            }
        });
    }

}
