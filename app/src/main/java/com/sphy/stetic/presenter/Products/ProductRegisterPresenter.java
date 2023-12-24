package com.sphy.stetic.presenter.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.contract.Products.ProductRegisterContract;
import com.sphy.stetic.model.Clients.ClientRegisterModel;
import com.sphy.stetic.model.Products.ProductRegisterModel;
import com.sphy.stetic.view.Clients.RegisterClientView;
import com.sphy.stetic.view.Products.RegisterProductView;

public class ProductRegisterPresenter implements ProductRegisterContract.Presenter {

    private RegisterProductView view;
    private ProductRegisterModel model;

    public ProductRegisterPresenter(RegisterProductView view) {
        this.view = view;
        model = new ProductRegisterModel(view);
    }

    @Override
    public void insertProduct(Product product) {
        model.insertProduct(product, new ProductRegisterModel.OnProductInsertedListener() {
            @Override
            public void onProductInsertedSuccess() {
                view.showInsertSuccessMessage();
                view.clearFields();
            }

            @Override
            public void onProductInsertedError(String message) {
                view.showInsertErrorMessage();
            }
        });
    }
}
