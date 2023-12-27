package com.sphy.stetic.model.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientListContract;
import com.sphy.stetic.contract.Products.ProductListContract;

import java.util.List;

public class ProductListModel implements ProductListContract.Model {

    private Context context;

    public ProductListModel(Context context){
        this.context = context;
    }

    @Override
    public void loadAllProducts(OnLoadProductsListener listener) {




        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        List<Product> products = db.productDao().getAll();
        // Si va bien
        listener.onLoadProductsSuccess(products);

    }
}
