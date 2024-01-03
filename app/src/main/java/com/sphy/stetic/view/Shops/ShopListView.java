package com.sphy.stetic.view.Shops;

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

import com.sphy.stetic.Adapter.ShopAdapter;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Shops.ShopListContract;
import com.sphy.stetic.presenter.Shops.ShopListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShopListView extends AppCompatActivity implements ShopListContract.View {

    private List<Shop> shops;
    private ShopAdapter adapter;
    private ShopListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        presenter = new ShopListPresenter(this);


        shops = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.shop_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShopAdapter(shops);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllShops();


    }

    public void addShop(View view) {
        Intent intent = new Intent(this, RegisterShopView.class);
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
            Intent intent = new Intent(ShopListView.this, SearchShopView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listShops(List<Shop> shops) {
        this.shops.clear();
        this.shops.addAll(shops);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}