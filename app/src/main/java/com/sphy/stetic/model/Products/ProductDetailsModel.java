package com.sphy.stetic.model.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Db.ProductDao;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.contract.Products.ProductDetailsContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsModel implements ProductDetailsContract.Model {

    private Context context;

    public ProductDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void getProductDetails(long id, OnProductDetailsListener listener) {

        ProductApiInterface api = ProductApi.buildInstance();
        Call<Product> getProductCall = api.getProductById(id);
        getProductCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Log.e("getProduct", response.message());
                Product product = response.body();
                listener.onProductDetailsSuccess(product);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("getProduct", t.getMessage());
                listener.onProductDetailsError("Se ha producido un error al conectar con el servidor");
            }
        });

    }


    @Override
    public void deleteProduct(long id, OnDeleteListener listener) {

        ProductApiInterface api = ProductApi.buildInstance();
        Call<Void> deleteProductCall = api.deleteProduct(id);
        deleteProductCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onDeleteSuccess();
                } else {
                    Log.e("deleteProduct", "Error al eliminar el producto: " + response.message());
                    listener.onDeleteError("Error al eliminar el producto");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("deleteProduct", "Error al conectar con el servidor: " + t.getMessage());
                listener.onDeleteError("Se ha producido un error al conectar con el servidor");
            }
        });
    }

}
