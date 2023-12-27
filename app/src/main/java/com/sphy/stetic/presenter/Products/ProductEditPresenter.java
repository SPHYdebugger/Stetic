package com.sphy.stetic.presenter.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientEditContract;
import com.sphy.stetic.contract.Products.ProductEditContract;
import com.sphy.stetic.model.Clients.ClientEditModel;
import com.sphy.stetic.model.Products.ProductEditModel;

public class ProductEditPresenter implements ProductEditContract.Presenter {
    private ProductEditContract.View view;
    private ProductEditContract.Model model;

    public ProductEditPresenter(ProductEditContract.View view) {
        this.view = view;
        this.model = new ProductEditModel();
    }



    @Override
    public void updateProduct(Product product) {
        model.updateProduct(product, new ProductEditModel.OnUpdateProductListener() {
            @Override
            public void onUpdateProductSuccess() {
                view.showUpdateSuccessMessage();
            }

            @Override
            public void onUpdateProductError(String message) {

            }
        });
    }

    @Override
    public void cancelEditing() {
        view.showCancelMessage();
    }
}
