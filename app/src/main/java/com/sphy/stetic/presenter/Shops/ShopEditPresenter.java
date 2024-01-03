package com.sphy.stetic.presenter.Shops;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.contract.Shops.ShopEditContract;
import com.sphy.stetic.model.Shops.ShopEditModel;
import com.sphy.stetic.view.Shops.ShopEditView;

public class ShopEditPresenter implements ShopEditContract.Presenter {

    private final ShopEditView view;
    private final ShopEditModel model;

    public ShopEditPresenter(ShopEditView view) {
        this.view = view;
        this.model = new ShopEditModel(view);
    }

    @Override
    public void getShopDetails(long shopId) {
        model.getShopDetails(shopId, new ShopEditContract.Model.OnShopDetailsListener() {
            @Override
            public void onShopDetailsSuccess(Shop shop) {
                view.displayShopDetails(shop);
            }
            @Override
            public void onShopDetailsError(String errorMessage) {

            }
        });
    }

    @Override
    public void updateShop(long id, Shop shop) {
        model.updateShop(id, shop, new ShopEditContract.Model.OnUpdateShopListener() {

            @Override
            public void onUpdateShopSuccess(String successMessage) {
                view.showUpdateErrorMessage();
            }
            @Override
            public void onUpdateShopError(String errorMessage) {
                view.showUpdateErrorMessage();
            }
        });
    }
}
