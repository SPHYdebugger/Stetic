package com.sphy.stetic.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.view.Clients.ClientListView;
import com.sphy.stetic.view.Shops.ShopListActivity;
import com.sphy.stetic.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.goClients){
                Intent intent = new Intent(MainActivity.this, ClientListView.class);
                startActivity(intent);
                return true;
        }
        if (item.getItemId() == R.id.goShops){
            Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

