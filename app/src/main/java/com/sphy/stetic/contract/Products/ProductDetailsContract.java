package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Product;

public interface ProductDetailsContract {
    interface View {
        void displayProductDetails(Product product);
        void showUpdateSuccessMessage();
        void showUpdateErrorMessage();
        void showDeleteSuccessMessage();
        void showDeleteErrorMessage();
    }

    interface Presenter {
        void getProductDetails(long id);
        void updateProduct(Product product);
        void deleteProduct(long id);
    }

    interface Model {
        interface OnProductDetailsListener {
            void onProductDetailsSuccess(Product product);
            void onProductDetailsError(String message);
        }

        interface OnUpdateListener {
            void onUpdateSuccess();
            void onUpdateError(String message);
        }

        interface OnDeleteListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void getProductDetails(long id, OnProductDetailsListener listener);
        void updateProduct(Product product, OnUpdateListener listener);
        void deleteProduct(long id, OnDeleteListener listener);
    }
}