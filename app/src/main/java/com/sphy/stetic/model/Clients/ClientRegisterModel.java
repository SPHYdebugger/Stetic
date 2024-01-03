package com.sphy.stetic.model.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.api.ClientApi;
import com.sphy.stetic.api.ClientApiInterface;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.contract.Products.ProductRegisterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRegisterModel implements ClientRegisterContract.Model {

    private Context context;

    public ClientRegisterModel(Context context) {
        this.context = context;
    }



    @Override
    public void insertClient(Client client, ClientRegisterContract.Model.OnClientInsertedListener listener) {
        ClientApiInterface api = ClientApi.buildInstance();
        Call<Client> addClientCall = api.addClient(client);
        addClientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                listener.onClientInsertedSuccess();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("addClient", t.getMessage());
                listener.onClientInsertedError("No se ha podido conectar con el servidor");
            }
        });
    }
}
