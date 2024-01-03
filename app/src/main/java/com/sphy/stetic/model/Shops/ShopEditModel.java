package com.sphy.stetic.model.Shops;

import android.content.Context;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.api.ShopApi;
import com.sphy.stetic.api.ShopApiInterface;
import com.sphy.stetic.contract.Shops.ShopDetailsContract;
import com.sphy.stetic.contract.Shops.ShopEditContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopEditModel implements ShopEditContract.Model {

    private Context context;

    public ShopEditModel(Context context) {
        this.context = context;
    }


    @Override
    public void updateShop(long id, Shop shop, ShopEditContract.Model.OnUpdateShopListener listener) {
        ShopApiInterface api = ShopApi.buildInstance();
        Call<Shop> editShopCall = api.editShopById(id, shop);
        editShopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.isSuccessful()) {
                    listener.onUpdateShopSuccess("Tienda modificada");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                listener.onUpdateShopError("Tienda modificado");
            }
        });
    }

    @Override
    public void getShopDetails(long id, OnShopDetailsListener listener) {

    }

    @Override
    public void getShopDetails(long id, ShopDetailsContract.Model.OnShopDetailsListener listener) {

        ShopApiInterface api = ShopApi.buildInstance();
        Call<Shop> getShopCall = api.getShopById(id);
        getShopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.isSuccessful()) {
                    listener.onShopDetailsSuccess(response.body());
                } else {
                    listener.onShopDetailsError("Error al obtener los detalles de la tienda");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                listener.onShopDetailsError("Error de conexión al obtener los detalles de la tienda");
            }
        });
    }
}
