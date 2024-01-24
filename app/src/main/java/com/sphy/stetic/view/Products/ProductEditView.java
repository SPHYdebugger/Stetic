package com.sphy.stetic.view.Products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Products.ProductEditContract;
import com.sphy.stetic.presenter.Products.ProductEditPresenter;

public class ProductEditView extends AppCompatActivity implements ProductEditContract.View {
    private ProductEditContract.Presenter presenter;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        presenter = new ProductEditPresenter(this);


        id = getIntent().getLongExtra("id", id);
        presenter.getProductDetails(id);

        Button modifyButton = findViewById(R.id.btnModify);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyProduct();
            }
        });
    }

    @Override
    public void displayProductDetails(Product product) {
        EditText etId = findViewById(R.id.edit_id);
        EditText etName = findViewById(R.id.edit_name);
        EditText etDescription = findViewById(R.id.edit_description);
        EditText etPrice = findViewById(R.id.edit_price);
        EditText etRegisterDate = findViewById(R.id.edit_registerDate);

        etId.setText(String.valueOf(product.getId()));
        etName.setText(product.getName());
        etDescription.setText(product.getDescription());
        etPrice.setText(String.valueOf(product.getPrice()));
        etRegisterDate.setText(product.getRegistrationDate());
    }



    private void modifyProduct() {
        EditText etName = findViewById(R.id.edit_name);
        EditText etDescription = findViewById(R.id.edit_description);
        EditText etPrice = findViewById(R.id.edit_price);

        Product updatedProduct = new Product();

        updatedProduct.setName(etName.getText().toString());
        updatedProduct.setDescription(etDescription.getText().toString());

        try {
            float priceValue = Float.parseFloat(etPrice.getText().toString());
            updatedProduct.setPrice(priceValue);
        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        presenter.updateProduct(id, updatedProduct);


    }
    @Override
    public void showUpdateSuccessMessage() {
        Toast.makeText(this, R.string.product_successful_modified, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ProductDetailsView.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUpdateErrorMessage() {
        Toast.makeText(this, R.string.product_successful_modified, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ProductDetailsView.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
