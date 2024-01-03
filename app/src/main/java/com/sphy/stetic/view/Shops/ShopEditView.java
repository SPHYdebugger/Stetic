package com.sphy.stetic.view.Shops;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Shops.ShopEditContract;
import com.sphy.stetic.presenter.Shops.ShopEditPresenter;

public class ShopEditView extends AppCompatActivity implements ShopEditContract.View {
    private ShopEditContract.Presenter presenter;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_edit);

        presenter = new ShopEditPresenter(this);


        id = getIntent().getLongExtra("id", id);
        presenter.getShopDetails(id);

        Button modifyButton = findViewById(R.id.btnModify);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyShop();
            }
        });
    }

    @Override
    public void displayShopDetails(Shop shop) {
        EditText etId = findViewById(R.id.edit_id);
        EditText etName = findViewById(R.id.edit_name);
        EditText etAddress = findViewById(R.id.edit_description);
        EditText etCity = findViewById(R.id.edit_price);
        EditText etSolarium = findViewById(R.id.edit_registerDate);
        EditText etLatitude = findViewById(R.id.edit_latitude);
        EditText etLongitude = findViewById(R.id.edit_longitude);

        etId.setText(String.valueOf(shop.getId()));
        etName.setText(shop.getName());
        etAddress.setText(shop.getAddress());
        etCity.setText(shop.getCity());
        etSolarium.setText(String.valueOf(etSolarium));
        etLatitude.setText(String.valueOf(shop.getLatitude()));
        etLongitude.setText(String.valueOf(shop.getLongitude()));
    }



    private void modifyShop() {
        EditText etName = findViewById(R.id.edit_name);
        EditText etAddress = findViewById(R.id.edit_address);
        EditText etCity = findViewById(R.id.edit_city);
        EditText etSolarium = findViewById(R.id.edit_solarium);
        EditText etLatitude = findViewById(R.id.edit_latitude);
        EditText etLongitude = findViewById(R.id.edit_longitude);

        Shop updatedShop = new Shop();

        updatedShop.setName(etName.getText().toString());
        updatedShop.setAddress(etAddress.getText().toString());
        updatedShop.setCity(etCity.getText().toString());
        String solarium = etSolarium.getText().toString();

        if (solarium.equals("false")){
            updatedShop.setSolarium(false);
        }
        if (solarium.equals("true")){
            updatedShop.setSolarium(true);
        }

        updatedShop.setLatitude(Double.valueOf(etLatitude.getText().toString()));
        updatedShop.setLongitude(Double.valueOf(etLongitude.getText().toString()));


        presenter.updateShop(id, updatedShop);


    }
    @Override
    public void showUpdateSuccessMessage() {
        Toast.makeText(this, "Tienda actualizada correctamente", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ShopDetailsView.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUpdateErrorMessage() {
        Toast.makeText(this, "Tienda actualizada correctamente", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ShopDetailsView.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
