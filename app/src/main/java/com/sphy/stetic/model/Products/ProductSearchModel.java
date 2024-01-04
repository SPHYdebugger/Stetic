package com.sphy.stetic.model.Products;


import com.sphy.stetic.Domain.Product;

import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Products.ProductSearchContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSearchModel implements ProductSearchContract.Model {

    @Override
    public void performSearch(int searchId, String searchName, OnSearchListener listener) {
        ProductApiInterface api = ProductApi.buildInstance();
        Call<List<Product>> searchCall;
        Call<Product> searchCallProduct;
        List<Product> productList = new ArrayList<>();

        if (!searchName.isEmpty() && searchId == 0) {
            searchCall = api.searchProductsByName(searchName);
            searchCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        listener.onSearchSuccess(response.body());
                    } else {
                        listener.onSearchError("No se encontró el producto");
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    listener.onSearchError("Error de conexión al realizar la búsqueda");
                }

            });
        } else if (searchName.isEmpty() && searchId != 0) {
            searchCallProduct = api.getProductById(searchId);
            searchCallProduct.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        Product product = response.body();
                        if (product != null) {
                            productList.add(product);
                            listener.onSearchSuccess(productList);
                        } else {
                            listener.onSearchError("Producto no encontrado");
                        }
                    } else {
                        listener.onSearchError("Error al realizar la búsqueda");
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    listener.onSearchError("Error de conexión al realizar la búsqueda");
                }


            });
        }
        if (searchName.equals("true")) {
            searchCall = api.searchProductsByDangerous(true);
            searchCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        listener.onSearchSuccess(response.body());
                    } else {
                        listener.onSearchError("Producto no encontrado");
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    listener.onSearchError("Error de conexión al realizar la búsqueda");
                }

            });
        } else if (searchName.equals("false")) {
            searchCall = api.searchProductsByDangerous(false);
            searchCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        listener.onSearchSuccess(response.body());
                    } else {
                        listener.onSearchError("Error al realizar la búsqueda");
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    listener.onSearchError("Error de conexión al realizar la búsqueda");
                }

            });
        }


    }
}
