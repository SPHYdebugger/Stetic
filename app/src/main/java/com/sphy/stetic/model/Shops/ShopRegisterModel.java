package com.sphy.stetic.model.Shops;

import android.content.Context;
import android.util.Log;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.api.ShopApi;
import com.sphy.stetic.api.ShopApiInterface;
import com.sphy.stetic.contract.Shops.ShopRegisterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRegisterModel implements ShopRegisterContract.Model {

    private Context context;

    public ShopRegisterModel(Context context) {
        this.context = context;
    }



    @Override
    public void insertShop(Shop shop, OnShopInsertedListener listener) {
        ShopApiInterface api = ShopApi.buildInstance();
        Call<Shop> addShopCall = api.addShop(shop);
        addShopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                listener.onShopInsertedSuccess();
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.e("addShop", t.getMessage());
                listener.onShopInsertedError("No se ha podido conectar con el servidor");
            }
        });
    }
}
