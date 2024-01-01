package com.sphy.stetic.presenter.Products;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.contract.Products.ProductDetailsContract;
import com.sphy.stetic.model.Clients.ClientDetailsModel;
import com.sphy.stetic.model.Products.ProductDetailsModel;

public class ProductDetailsPresenter implements ProductDetailsContract.Presenter {

    private ProductDetailsContract.View view;
    private ProductDetailsContract.Model model;

    public ProductDetailsPresenter(ProductDetailsContract.View view) {
        this.view = view;
        this.model = new ProductDetailsModel(((AppCompatActivity) view).getApplicationContext());
    }

    @Override
    public void getProductDetails(long  id) {
        model.getProductDetails(id, new ProductDetailsModel.OnProductDetailsListener() {
            @Override
            public void onProductDetailsSuccess(Product product) {
                view.displayProductDetails(product);
            }

            @Override
            public void onProductDetailsError(String message) {

            }


        });
    }



    @Override
    public void deleteProduct(long id) {
        model.deleteProduct(id, new ProductDetailsModel.OnDeleteListener() {
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
