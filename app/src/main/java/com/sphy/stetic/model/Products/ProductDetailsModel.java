package com.sphy.stetic.model.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Db.ProductDao;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.contract.Products.ProductDetailsContract;

public class ProductDetailsModel implements ProductDetailsContract.Model {

    private Context context;

    public ProductDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void getProductDetails(String id, OnProductDetailsListener listener) {
        // Lógica para obtener detalles del cliente por su DNI
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Product product = db.productDao().findById(id);

        if (product != null) {
            listener.onProductDetailsSuccess(product);
        } else {
            listener.onProductDetailsError("Producto no encontrado");
        }
    }



    @Override
    public void updateProduct(Product product, OnUpdateListener listener) {
        // Lógica para actualizar el producto
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        try {
            db.productDao().update(product);
            listener.onUpdateSuccess();
        } catch (Exception e) {
            listener.onUpdateError("Error al actualizar el producto");
        }
    }

    @Override
    public void deleteProduct(String id, OnDeleteListener listener) {
        // Lógica para eliminar el producto por su id
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        try {
            db.productDao().deleteById(id);
            listener.onDeleteSuccess();
        } catch (Exception e) {
            listener.onDeleteError("Error al eliminar el producto");
        }
    }
}
