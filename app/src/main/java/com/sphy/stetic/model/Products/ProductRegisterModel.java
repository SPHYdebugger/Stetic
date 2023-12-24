package com.sphy.stetic.model.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.contract.Products.ProductRegisterContract;

public class ProductRegisterModel implements ProductRegisterContract.Model {

    private Context context;

    public ProductRegisterModel(Context context) {
        this.context = context;
    }



    @Override
    public void insertProduct(Product product, OnProductInsertedListener listener) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.productDao().insert(product);
        listener.onProductInsertedSuccess();
    }
}
