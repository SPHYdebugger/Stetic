package com.sphy.stetic.presenter.Shops;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.contract.Shops.ShopDetailsContract;
import com.sphy.stetic.model.Shops.ShopDetailsModel;

public class ShopDetailsPresenter implements ShopDetailsContract.Presenter {

    private ShopDetailsContract.View view;
    private ShopDetailsContract.Model model;

    public ShopDetailsPresenter(ShopDetailsContract.View view) {
        this.view = view;
        this.model = new ShopDetailsModel(((AppCompatActivity) view).getApplicationContext());
    }

    @Override
    public void getShopDetails(long  id) {
        model.getShopDetails(id, new ShopDetailsModel.OnShopDetailsListener() {
            @Override
            public void onShopDetailsSuccess(Shop shop) {
                view.displayShopDetails(shop);
            }

            @Override
            public void onShopDetailsError(String message) {

            }





        });
    }



    @Override
    public void deleteShop(long id) {
        model.deleteShop(id, new ShopDetailsModel.OnDeleteListener() {
            @Override
            public void onDeleteSuccess() {
                view.showDeleteSuccessMessage();
            }

            @Override
            public void onDeleteError(String message) {
                view.showDeleteErrorMessage();
            }
        });
    }
}
