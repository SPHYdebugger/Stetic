package com.sphy.stetic.contract.Shops;

import com.sphy.stetic.Domain.Shop;

public interface ShopRegisterContract {

    interface View {
        void showInsertSuccessMessage();

        void showInsertErrorMessage();

        void clearFields();
    }

    interface Presenter {
        void insertShop(Shop shop);
    }

    interface Model {
        interface OnShopInsertedListener {
            void onShopInsertedSuccess();

            void onShopInsertedError(String message);
        }

        void insertShop(Shop shop, OnShopInsertedListener listener);
    }
}
