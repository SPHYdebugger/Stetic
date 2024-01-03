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
import com.sphy.stetic.contract.Clients.ClientListContract;
import com.sphy.stetic.contract.Products.ProductListContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListModel implements ClientListContract.Model {

    private Context context;

    public ClientListModel(Context context){
        this.context = context;
    }

    @Override
    public void loadAllClients(OnLoadClientsListener listener) {
        ClientApiInterface api = ClientApi.buildInstance();
        Call<List<Client>> getClientCall = api.getClients();
        getClientCall.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                Log.e("getClients", response.message());
                List<Client> clients = response.body();
                listener.onLoadClientsSuccess(clients);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("getClients", t.getMessage());
                listener.onLoadClientsError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
