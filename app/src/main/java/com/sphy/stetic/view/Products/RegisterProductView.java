package com.sphy.stetic.view.Products;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.contract.Products.ProductRegisterContract;
import com.sphy.stetic.presenter.Clients.ClientRegisterPresenter;
import com.sphy.stetic.presenter.Products.ProductRegisterPresenter;

import java.time.LocalDate;

public class RegisterProductView extends AppCompatActivity implements ProductRegisterContract.View {

    private ProductRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);

        presenter = new ProductRegisterPresenter(this);
    }

    public void createProduct(View view) {

        EditText etName = findViewById(R.id.product_name);
        EditText etSize = findViewById(R.id.product_size);
        EditText etDescription = findViewById(R.id.product_description);
        EditText etPrice = findViewById(R.id.product_price);
        CheckBox cbDangerous = findViewById(R.id.product_dangerous);


        String name = etName.getText().toString();
        int size = Integer.parseInt(etSize.getText().toString());
        String description = etDescription.getText().toString();
        float price = Float.parseFloat(etPrice.getText().toString());
        boolean dangerous = cbDangerous.isChecked();


        Product product = new Product(name, size, description, price, dangerous);
        presenter.insertProduct(product);
    }

    @Override
    public void showInsertSuccessMessage() {
        Toast.makeText(this,"Producto insertado correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInsertErrorMessage() {
        Toast.makeText(this, "Error al insertar el producto", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearFields() {


        EditText etName = findViewById(R.id.product_name);
        EditText etSize = findViewById(R.id.product_size);
        EditText etDescription = findViewById(R.id.product_description);
        EditText etPrice = findViewById(R.id.product_price);
        CheckBox cbDangerous = findViewById(R.id.product_dangerous);



        etName.setText("");
        etSize.setText("");
        etDescription.setText("");
        etPrice.setText("");
        cbDangerous.clearFocus();


        etName.requestFocus();
    }
}
