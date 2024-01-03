package com.sphy.stetic.contract.Shops;

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Domain.Shop;

public interface ShopDetailsContract {
    interface View {
        void displayShopDetails(Shop shop);

        void showDeleteSuccessMessage();
        void showDeleteErrorMessage();
    }

    interface Presenter {
        void getShopDetails(long id);

        void deleteShop(long id);
    }

    interface Model {
        interface OnShopDetailsListener {
            void onShopDetailsSuccess(Shop shop);
            void onShopDetailsError(String message);
        }

        interface OnDeleteListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void getShopDetails(long id, OnShopDetailsListener listener);

        void deleteShop(long id, OnDeleteListener listener);
    }
}