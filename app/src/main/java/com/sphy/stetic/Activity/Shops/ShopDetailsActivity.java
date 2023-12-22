package com.sphy.stetic.Activity.Shops;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Activity.Clients.ClientEditActivity;
import com.sphy.stetic.Activity.Clients.ClientListActivity;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;

public class ShopDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        Intent intent = getIntent();
        String shopName = intent.getStringExtra("name");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Shop shop = db.shopDao().findByName(shopName);
        loadShop(shop);

        Button backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetailsActivity.this, ShopListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadShop(Shop shop) {

        String name = shop.getName();
        String address = shop.getAddress();
        String city = shop.getCity();
        boolean isSolarium = shop.isSolarium();


        TextView tvname = findViewById(R.id.detail_name);
        TextView tvAddress = findViewById(R.id.detail_address);
        TextView tvCity = findViewById(R.id.detail_city);
        TextView tvSolarium = findViewById(R.id.detail_solarium);


        tvname.setText(name);
        tvAddress.setText(address);
        tvCity.setText(city);
        tvSolarium.setText(isSolarium ? "Sí" : "No");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit){

            return true;
        }
         if (item.getItemId() == R.id.delete) {
         Intent intent = getIntent();
         String shopName = intent.getStringExtra("name");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.shopDao().deleteByName(shopName);
        finish();
        return true;
    }
        return super.onOptionsItemSelected(item);
    }
}