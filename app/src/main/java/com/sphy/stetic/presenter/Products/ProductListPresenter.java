package com.sphy.stetic.presenter.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientListContract;
import com.sphy.stetic.contract.Products.ProductListContract;
import com.sphy.stetic.model.Clients.ClientListModel;
import com.sphy.stetic.model.Products.ProductListModel;
import com.sphy.stetic.view.Clients.ClientListView;
import com.sphy.stetic.view.Products.ProductListView;

import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter, ProductListContract.Model.OnLoadProductsListener{

    private ProductListView view;
    private ProductListModel model;

    public ProductListPresenter(ProductListView view){
        this.view = view;
        model= new ProductListModel(view);
    }

    @Override
    public void loadAllProducts() {
        model.loadAllProducts(this);
    }



    @Override
    public void onLoadProductsSuccess(List<Product> products) {
        view.listProducts(products);
    }

    @Override
    public void onLoadProductsError(String message) {
        view.showMessage(message);
    }
}
