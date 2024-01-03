package com.sphy.stetic.presenter.Shops;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.contract.Shops.ShopListContract;
import com.sphy.stetic.model.Shops.ShopListModel;
import com.sphy.stetic.view.Shops.ShopListView;

import java.util.List;

public class ShopListPresenter implements ShopListContract.Presenter, ShopListContract.Model.OnLoadShopsListener{

    private ShopListView view;
    private ShopListModel model;

    public ShopListPresenter(ShopListView view){
        this.view = view;
        model= new ShopListModel(view);
    }

    @Override
    public void loadAllShops() {
        model.loadAllShops(this);
    }



    @Override
    public void onLoadShopsSuccess(List<Shop> shops) {
        view.listShops(shops);
    }

    @Override
    public void onLoadShopsError(String message) {
        view.showMessage(message);
    }
}
