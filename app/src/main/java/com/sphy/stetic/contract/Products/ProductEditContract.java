package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Product;

public interface ProductEditContract {
    interface View {
        void showUpdateSuccessMessage();
        void showUpdateErrorMessage();
        void displayProductDetails(Product product);
    }

    interface Presenter {
        void getProductDetails(long id);

        void updateProduct(long id, Product product);


    }

    interface Model {
        void getProductDetails(long id, OnProductDetailsListener listener);


        interface OnUpdateProductListener {
            void onUpdateProductSuccess(String successMessage);

            void onUpdateProductError(String errorMessage);
        }
        interface OnProductDetailsListener {
            void onProductDetailsSuccess(Product product);
            void onProductDetailsError(String errorMessage);
        }


        void updateProduct(long id, Product product, OnUpdateProductListener listener);

    }



}
