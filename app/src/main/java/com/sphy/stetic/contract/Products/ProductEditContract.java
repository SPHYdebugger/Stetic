package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;

public interface ProductEditContract {
    interface View {
        void showUpdateSuccessMessage();

        void showUpdateErrorMessage();

        void showCancelMessage();

        void clearFields();
    }

    interface Presenter {
        void updateProduct(Product product);

        void cancelEditing();
    }

    interface Model {
        interface OnUpdateProductListener {
            void onUpdateProductSuccess();

            void onUpdateProductError(String message);
        }

        void updateProduct(Product product, OnUpdateProductListener listener);
    }
}


