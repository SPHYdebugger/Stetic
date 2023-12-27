package com.sphy.stetic.view.Products;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.contract.Products.ProductDetailsContract;
import com.sphy.stetic.presenter.Clients.ClientDetailsPresenter;
import com.sphy.stetic.presenter.Products.ProductDetailsPresenter;

public class ProductDetailsView extends AppCompatActivity implements ProductDetailsContract.View {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvRegisterDate;




    private ProductDetailsContract.Presenter presenter;
    private String productId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        tvId = findViewById(R.id.detail_id);
        tvName = findViewById(R.id.detail_name);
        tvDescription = findViewById(R.id.detail_description);
        tvPrice = findViewById(R.id.detail_price);
        tvRegisterDate = findViewById(R.id.detail_registerData);



        presenter = new ProductDetailsPresenter(this);

        Intent intent = getIntent();
        productId = intent.getStringExtra("id");
        presenter.getProductDetails(productId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        productId = intent.getStringExtra("id");
        presenter.getProductDetails(productId);
    }

    @Override
    public void displayProductDetails(Product product) {
        tvId.setText(product.getId());
        tvName.setText(product.getName());
        tvDescription.setText(product.getDescription());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvRegisterDate.setText(String.valueOf(product.getRegisterDate()));

    }

    @Override
    public void showUpdateSuccessMessage() {
        Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateErrorMessage() {
        Toast.makeText(this, "Error al actualizar el Producto", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDeleteSuccessMessage() {
        Toast.makeText(this, "Producto eliminado correctamente", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showDeleteErrorMessage() {
        Toast.makeText(this, "Error al eliminar el producto", Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent intent = new Intent(ProductDetailsView.this, ProductEditView.class);
            intent.putExtra("id", productId);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.delete) {
            presenter.deleteProduct(productId);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}