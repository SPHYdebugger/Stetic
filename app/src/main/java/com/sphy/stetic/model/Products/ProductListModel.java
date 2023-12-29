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
import com.sphy.stetic.contract.Clients.ClientListContract;
import com.sphy.stetic.contract.Products.ProductListContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListModel implements ProductListContract.Model {

    private Context context;

    public ProductListModel(Context context){
        this.context = context;
    }

    @Override
    public void loadAllProducts(OnLoadProductsListener listener) {
        ProductApiInterface api = ProductApi.buildInstance();
        Call<List<Product>> getProductCall = api.getProducts();
        getProductCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.e("getProducts", response.message());
                List<Product> products = response.body();
                listener.onLoadProductsSuccess(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("getProducts", t.getMessage());
                listener.onLoadProductsError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
