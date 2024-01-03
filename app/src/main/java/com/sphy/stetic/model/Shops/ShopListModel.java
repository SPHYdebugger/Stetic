package com.sphy.stetic.model.Shops;

import android.content.Context;
import android.util.Log;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.api.ShopApi;
import com.sphy.stetic.api.ShopApiInterface;
import com.sphy.stetic.contract.Shops.ShopListContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListModel implements ShopListContract.Model {

    private Context context;

    public ShopListModel(Context context){
        this.context = context;
    }

    @Override
    public void loadAllShops(OnLoadShopsListener listener) {
        ShopApiInterface api = ShopApi.buildInstance();
        Call<List<Shop>> getShopCall = api.getShops();
        getShopCall.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                Log.e("getShops", response.message());
                List<Shop> shops = response.body();
                listener.onLoadShopsSuccess(shops);
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Log.e("getShops", t.getMessage());
                listener.onLoadShopsError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
