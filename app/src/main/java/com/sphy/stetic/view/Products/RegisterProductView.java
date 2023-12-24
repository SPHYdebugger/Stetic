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
        EditText etId = findViewById(R.id.product_id);
        EditText etName = findViewById(R.id.product_name);
        EditText etDescription = findViewById(R.id.product_description);
        EditText etPrice = findViewById(R.id.product_price);
        EditText etRegisterDate = findViewById(R.id.product_registerDate);

        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        double price = Double.parseDouble(etPrice.getText().toString());
        String registerDate = etRegisterDate.getText().toString();


        Product product = new Product(id, name, description, price, registerDate);
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
        EditText etId = findViewById(R.id.product_id);
        EditText etName = findViewById(R.id.product_name);
        EditText etDescription = findViewById(R.id.product_description);
        EditText etPrice = findViewById(R.id.product_price);
        EditText etRegisterDate = findViewById(R.id.product_registerDate);



        etId.setText("");
        etName.setText("");
        etDescription.setText("");
        etPrice.setText("");
        etRegisterDate.setText("");


        etId.requestFocus();
    }
}
