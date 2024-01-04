package com.sphy.stetic.presenter.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Products.ProductSearchContract;
import com.sphy.stetic.model.Products.ProductSearchModel;

import java.util.List;

public class ProductSearchPresenter implements ProductSearchContract.Presenter {

    private final ProductSearchContract.View view;
    private final ProductSearchModel model;

    public ProductSearchPresenter(ProductSearchContract.View view) {
        this.view = view;
        this.model = new ProductSearchModel();
    }

    @Override
    public void performSearch(int searchId, String searchName) {
        model.performSearch(searchId, searchName, new ProductSearchContract.Model.OnSearchListener() {
            @Override
            public void onSearchSuccess(List<Product> searchResults) {
                view.displaySearchResults(searchResults);
            }

            @Override
            public void onSearchError(String errorMessage) {
                view.displaySearchError("Producto no encontrado");
            }
        });
    }
}
