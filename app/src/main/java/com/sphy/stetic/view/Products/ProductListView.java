package com.sphy.stetic.view.Products;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sphy.stetic.Adapter.ClientAdapter;
import com.sphy.stetic.Adapter.ProductAdapter;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientListContract;
import com.sphy.stetic.contract.Products.ProductListContract;
import com.sphy.stetic.presenter.Clients.ClientListPresenter;
import com.sphy.stetic.presenter.Products.ProductListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductListView extends AppCompatActivity implements ProductListContract.View {

    private List<Product> products;
    private ProductAdapter adapter;
    private ProductListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        presenter = new ProductListPresenter(this);


        products = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllProducts();


    }

    public void addProduct(View view) {
        Intent intent = new Intent(this, RegisterProductView.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search){
            Intent intent = new Intent(ProductListView.this, SearchProductView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.listFavorite){
            Intent intent = new Intent(ProductListView.this, ProductListFavoritesView.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listProducts(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}