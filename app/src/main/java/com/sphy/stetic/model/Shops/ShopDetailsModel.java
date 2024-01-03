package com.sphy.stetic.model.Shops;

import android.content.Context;
import android.util.Log;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.api.ShopApi;
import com.sphy.stetic.api.ShopApiInterface;
import com.sphy.stetic.contract.Shops.ShopDetailsContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailsModel implements ShopDetailsContract.Model {

    private Context context;

    public ShopDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void getShopDetails(long id, OnShopDetailsListener listener) {

        ShopApiInterface api = ShopApi.buildInstance();
        Call<Shop> getShopCall = api.getShopById(id);
        getShopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                Log.e("getShop", response.message());
                Shop shop = response.body();
                listener.onShopDetailsSuccess(shop);
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.e("getShop", t.getMessage());
                listener.onShopDetailsError("Se ha producido un error al conectar con el servidor");
            }
        });

    }


    @Override
    public void deleteShop(long id, OnDeleteListener listener) {

        ShopApiInterface api = ShopApi.buildInstance();
        Call<Void> deleteShopCall = api.deleteShop(id);
        deleteShopCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onDeleteSuccess();
                } else {
                    Log.e("deleteShop", "Error al eliminar la tienda: " + response.message());
                    listener.onDeleteError("Error al eliminar la tienda");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("deleteShop", "Error al conectar con el servidor: " + t.getMessage());
                listener.onDeleteError("Se ha producido un error al conectar con el servidor");
            }
        });
    }

}
