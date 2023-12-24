package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;

public interface ProductRegisterContract {

    interface View {
        void showInsertSuccessMessage();

        void showInsertErrorMessage();

        void clearFields();
    }

    interface Presenter {
        void insertProduct(Product product);
    }

    interface Model {
        interface OnProductInsertedListener {
            void onProductInsertedSuccess();

            void onProductInsertedError(String message);
        }

        void insertProduct(Product product, OnProductInsertedListener listener);
    }
}
