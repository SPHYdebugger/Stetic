package com.sphy.stetic.contract.Shops;

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.Domain.Shop;

import java.util.List;

public interface ShopListContract {

    interface View{
        void listShops(List<Shop> shops);
        void showMessage(String message);
    }
    interface Presenter{
        void loadAllShops();
    }
    interface Model{
        interface OnLoadShopsListener{
            void onLoadShopsSuccess(List<Shop> shops);
            void onLoadShopsError(String message);
        }
        void loadAllShops(OnLoadShopsListener listener);
    }

}
