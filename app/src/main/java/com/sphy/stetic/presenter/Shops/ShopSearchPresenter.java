package com.sphy.stetic.presenter.Shops;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.contract.Shops.ShopSearchContract;
import com.sphy.stetic.model.Shops.ShopSearchModel;

import java.util.List;

public class ShopSearchPresenter implements ShopSearchContract.Presenter {

    private final ShopSearchContract.View view;
    private final ShopSearchModel model;

    public ShopSearchPresenter(ShopSearchContract.View view) {
        this.view = view;
        this.model = new ShopSearchModel();
    }

    @Override
    public void performSearch(int searchId, String searchCity) {
        model.performSearch(searchId, searchCity, new ShopSearchContract.Model.OnSearchListener() {
            @Override
            public void onSearchSuccess(List<Shop> searchResults) {
                view.displaySearchResults(searchResults);
            }

            @Override
            public void onSearchError(String errorMessage) {
                view.displaySearchError("No se pudo realizar la búsqueda. Mensaje de error: 6" + errorMessage);
            }
        });
    }
}
