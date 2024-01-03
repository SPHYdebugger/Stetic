package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Product;

public interface ProductDetailsContract {
    interface View {
        void displayProductDetails(Product product);

        void showDeleteSuccessMessage();
        void showDeleteErrorMessage();
    }

    interface Presenter {
        void getProductDetails(long id);

        void deleteProduct(long id);
    }

    interface Model {
        interface OnProductDetailsListener {
            void onProductDetailsSuccess(Product product);
            void onProductDetailsError(String message);
        }

        interface OnDeleteListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void getProductDetails(long id, OnProductDetailsListener listener);

        void deleteProduct(long id, OnDeleteListener listener);
    }
}