package com.sphy.stetic.view.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Products.ProductEditContract;
import com.sphy.stetic.presenter.Products.ProductEditPresenter;

public class ProductEditView extends AppCompatActivity implements ProductEditContract.View {
    private Product product;
    private ProductEditContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        Intent intent = getIntent();
        String productId = intent.getStringExtra("id");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        product = db.productDao().findById(productId);
        loadProduct(product);

        presenter = new ProductEditPresenter(this);

        Button cancelButton = findViewById(R.id.btnCancel);
        Button modifyButton = findViewById(R.id.btnModify);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancelEditing();
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyProduct();
            }
        });
    }

    private void loadProduct(Product product) {
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
        EditText etRegisterDate = findViewById(R.id.edit_registerDate);


        product.setName(etName.getText().toString());
        product.setDescription(etDescription.getText().toString());
        // Intenta convertir el texto a float
        try {
            float priceValue = Float.parseFloat(etPrice.getText().toString());
            product.setPrice(priceValue);
        } catch (NumberFormatException e) {
            //TODO capturar la excepcion
        }
        product.setRegistrationDate(etRegisterDate.getText().toString());


        // Actualizar el cliente en la base de datos
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.productDao().update(product);


        finish();
    }

    @Override
    public void showUpdateSuccessMessage() {

    }

    @Override
    public void showUpdateErrorMessage() {

    }

    @Override
    public void showCancelMessage() {

    }

    @Override
    public void clearFields() {

    }
}
