package com.sphy.stetic.view.Products;

import static com.sphy.stetic.Db.ConstantsDb.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Db.ProductDao;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Products.ProductDetailsContract;
import com.sphy.stetic.presenter.Products.ProductDetailsPresenter;

public class ProductDetailsView extends AppCompatActivity implements ProductDetailsContract.View {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvRegisterDate;




    private ProductDetailsContract.Presenter presenter;
    private long id;
    private Product temporalProduct;
    private ProductDao productDao;


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
        id = intent.getLongExtra("id", id);
        presenter.getProductDetails(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        id = intent.getLongExtra("id",id);
        presenter.getProductDetails(id);
    }

    @Override
    public void displayProductDetails(Product product) {
        tvId.setText(String.valueOf(product.getId()));
        tvName.setText(product.getName());
        tvDescription.setText(product.getDescription());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvRegisterDate.setText(product.getRegistrationDate());

        temporalProduct = product;

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
            intent.putExtra("id", id);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.delete) {
            confirmDeleteProduct();
            return true;
        }

        if (item.getItemId() == R.id.favorite) {
            addProductInDatabase(temporalProduct);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addProductInDatabase(Product product) {

        new Thread(() -> {

            AppDatabase db = Room.databaseBuilder(ProductDetailsView.this, AppDatabase.class, DATABASE_NAME).build();
            db.productDao().insert(product);


        }).start();
        Toast.makeText(this, R.string.product_added_favorites, Toast.LENGTH_LONG).show();
    }

    private void confirmDeleteProduct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmation);
        builder.setMessage(R.string.delete_product_alert);

        // Botón confirmar
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteProduct(id);
            }
        });

        // Botón cancelar
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Mostrar el aviso
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void backProducts(View view) {
        Intent intent = new Intent(this, ProductListView.class);
        startActivity(intent);
    }

}