package com.sphy.stetic.contract.Shops;

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.contract.Products.ProductEditContract;

public interface ShopEditContract {
    interface View {
        void showUpdateSuccessMessage();
        void showUpdateErrorMessage();
        void displayShopDetails(Shop shop);
    }

    interface Presenter {
        void getShopDetails(long id);

        void updateShop(long id, Shop shop);


    }

    interface Model {
        void getShopDetails(long id, ShopEditContract.Model.OnShopDetailsListener listener);

        void getShopDetails(long id, ShopDetailsContract.Model.OnShopDetailsListener listener);


        interface OnUpdateShopListener {
            void onUpdateShopSuccess(String successMessage);

            void onUpdateShopError(String errorMessage);
        }
        interface OnShopDetailsListener {
            void onShopDetailsSuccess(Shop shop);
            void onShopDetailsError(String errorMessage);
        }


        void updateShop(long id, Shop shop, ShopEditContract.Model.OnUpdateShopListener listener);

    }

}
