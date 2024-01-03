package com.sphy.stetic.presenter.Shops;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.contract.Shops.ShopRegisterContract;
import com.sphy.stetic.model.Shops.ShopRegisterModel;
import com.sphy.stetic.view.Shops.RegisterShopView;


public class ShopRegisterPresenter implements ShopRegisterContract.Presenter {

    private RegisterShopView view;
    private ShopRegisterModel model;

    public ShopRegisterPresenter(RegisterShopView view) {
        this.view = view;
        model = new ShopRegisterModel(view);
    }

    @Override
    public void insertShop(Shop shop) {
        model.insertShop(shop, new ShopRegisterModel.OnShopInsertedListener() {
            @Override
            public void onShopInsertedSuccess() {
                view.showInsertSuccessMessage();
                view.clearFields();
            }

            @Override
            public void onShopInsertedError(String message) {
                view.showInsertErrorMessage();
            }
        });
    }
}
