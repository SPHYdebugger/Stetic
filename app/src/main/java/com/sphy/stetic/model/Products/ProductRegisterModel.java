package com.sphy.stetic.model.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.contract.Products.ProductRegisterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRegisterModel implements ProductRegisterContract.Model {

    private Context context;

    public ProductRegisterModel(Context context) {
        this.context = context;
    }



    @Override
    public void insertProduct(Product product, OnProductInsertedListener listener) {
        ProductApiInterface api = ProductApi.buildInstance();
        Call<Product> addProductCall = api.addProduct(product);
        addProductCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                listener.onProductInsertedSuccess();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("addProduct", t.getMessage());
                listener.onProductInsertedError("No se ha podido conectar con el servidor");
            }
        });
    }
}
