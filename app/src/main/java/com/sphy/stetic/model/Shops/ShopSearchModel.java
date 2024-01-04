package com.sphy.stetic.model.Shops;


import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.api.ShopApi;
import com.sphy.stetic.api.ShopApiInterface;
import com.sphy.stetic.contract.Shops.ShopSearchContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopSearchModel implements ShopSearchContract.Model {

    @Override
    public void performSearch(int searchId, String searchCity, OnSearchListener listener) {
        ShopApiInterface api = ShopApi.buildInstance();
        Call<List<Shop>> searchCallShops;
        Call<Shop> searchCallShop;
        List<Shop> shopList = new ArrayList<>();

        if (!searchCity.isEmpty() && searchId == 0) {
            searchCallShops = api.getShopsByCity(searchCity);
            searchCallShops.enqueue(new Callback<List<Shop>>() {
                @Override
                public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                    if (response.isSuccessful()) {
                        listener.onSearchSuccess(response.body());
                    } else {
                        listener.onSearchError("Error al realizar la búsqueda");
                    }
                }

                @Override
                public void onFailure(Call<List<Shop>> call, Throwable t) {
                    listener.onSearchError("Error de conexión al realizar la búsqueda");
                }

            });
        } else if (searchCity.isEmpty() && searchId != 0) {
            searchCallShop = api.getShopById(searchId);
            searchCallShop.enqueue(new Callback<Shop>() {
                @Override
                public void onResponse(Call<Shop> call, Response<Shop> response) {
                    if (response.isSuccessful()) {
                        Shop shop = response.body();
                        if (shop != null) {
                            shopList.add(shop);
                            listener.onSearchSuccess(shopList);
                        } else {
                            listener.onSearchError("Tienda no encontrado 1");
                        }
                    } else {
                        listener.onSearchError("Error al realizar la búsqueda 2");
                    }
                }

                @Override
                public void onFailure(Call<Shop> call, Throwable t) {
                    listener.onSearchError("Error de conexión al realizar la búsqueda 3");
                }


            });
        }



    }
}
