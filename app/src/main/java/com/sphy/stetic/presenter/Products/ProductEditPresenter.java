package com.sphy.stetic.presenter.Products;

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Products.ProductEditContract;
import com.sphy.stetic.model.Products.ProductEditModel;
import com.sphy.stetic.view.Products.ProductEditView;

public class ProductEditPresenter implements ProductEditContract.Presenter {

    private final ProductEditView view;
    private final ProductEditModel model;

    public ProductEditPresenter(ProductEditView view) {
        this.view = view;
        this.model = new ProductEditModel(view);
    }

    @Override
    public void getProductDetails(long productId) {
        model.getProductDetails(productId, new ProductEditContract.Model.OnProductDetailsListener() {
            @Override
            public void onProductDetailsSuccess(Product product) {
                view.displayProductDetails(product);
            }
            @Override
            public void onProductDetailsError(String errorMessage) {

            }
        });
    }

    @Override
    public void updateProduct(long id, Product product) {
        model.updateProduct(id, product, new ProductEditContract.Model.OnUpdateProductListener() {

            @Override
            public void onUpdateProductSuccess(String successMessage) {
                view.showUpdateErrorMessage();
            }
            @Override
            public void onUpdateProductError(String errorMessage) {
                view.showUpdateErrorMessage();
            }
        });
    }
}
