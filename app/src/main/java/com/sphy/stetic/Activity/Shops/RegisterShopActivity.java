package com.sphy.stetic.Activity.Shops;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;

public class RegisterShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop);
    }

    public void createTask(View view) {
        EditText etName = findViewById(R.id.shop_name);
        EditText etAddress = findViewById(R.id.shop_address);
        EditText etCity = findViewById(R.id.shop_city);
        CheckBox checkSolarium = findViewById(R.id.shop_solarium);

        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String city = etCity.getText().toString();
        boolean solarium = checkSolarium.isChecked();

        Shop shop = new Shop(name,address,city,solarium);
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.shopDao().insert(shop);

        Toast.makeText(this, R.string.shop_insert, Toast.LENGTH_LONG).show();

        etName.setText("");
        etAddress.setText("");
        etCity.setText("");



        etName.requestFocus();
    }
}