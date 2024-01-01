package com.sphy.stetic.model.Products;

import android.content.Context;

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Products.ProductDetailsContract;
import com.sphy.stetic.contract.Products.ProductEditContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductEditModel implements ProductEditContract.Model {

    private Context context;

    public ProductEditModel(Context context) {
        this.context = context;
    }


    @Override
    public void updateProduct(long id, Product product, OnUpdateProductListener listener) {
        ProductApiInterface api = ProductApi.buildInstance();
        Call<Product> editProductCall = api.editProductById(id, product);
        editProductCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    listener.onUpdateProductSuccess("Producto modificado");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                listener.onUpdateProductError("Producto modificado");
            }
        });
    }

    @Override
    public void getProductDetails(long id, OnProductDetailsListener listener) {

        ProductApiInterface api = ProductApi.buildInstance();
        Call<Product> getProductCall = api.getProductById(id);
        getProductCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    listener.onProductDetailsSuccess(response.body());
                } else {
                    listener.onProductDetailsError("Error al obtener los detalles del producto");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                listener.onProductDetailsError("Error de conexión al obtener los detalles del producto");
            }
        });
    }
}
