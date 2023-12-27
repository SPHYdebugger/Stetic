package com.sphy.stetic.model.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.contract.Clients.ClientEditContract;
import com.sphy.stetic.contract.Products.ProductEditContract;

public class ProductEditModel implements ProductEditContract.Model {
    @Override
    public void updateProduct(Product product, ProductEditContract.Model.OnUpdateProductListener listener) {

    }
}
