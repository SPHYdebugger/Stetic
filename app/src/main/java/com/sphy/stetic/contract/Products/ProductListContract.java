package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;

import java.util.List;

public interface ProductListContract {

    interface View{
        void listProducts(List<Product> products);
        void showMessage(String message);
    }
    interface Presenter{
        void loadAllProducts();
    }
    interface Model{
        interface OnLoadProductsListener{
            void onLoadProductsSuccess(List<Product> products);
            void onLoadProductsError(String message);
        }
        void loadAllProducts(OnLoadProductsListener listener);
    }

}
