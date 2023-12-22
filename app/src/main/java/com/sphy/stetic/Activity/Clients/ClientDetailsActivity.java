package com.sphy.stetic.Activity.Clients;

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

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;

public class ClientDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        Intent intent = getIntent();
        String clientDni = intent.getStringExtra("dni");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Client client = db.clientDao().findByDni(clientDni);
        loadClient(client);

        Button backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDetailsActivity.this, ClientListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadClient(Client client) {

        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String dni = client.getDni();
        String address = client.getAddress();
        String city = client.getCity();
        String birthday = client.getBirthDay();
        boolean isVip = client.isVip();


        TextView tvFirstName = findViewById(R.id.detail_firstName);
        TextView tvLastName = findViewById(R.id.detail_lastName);
        TextView tvDni = findViewById(R.id.detail_dni);
        TextView tvAddress = findViewById(R.id.detail_address);
        TextView tvCity = findViewById(R.id.detail_city);
        TextView tvBirthday = findViewById(R.id.detail_birthday);
        TextView tvVip = findViewById(R.id.detail_vip);


        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvDni.setText(dni);
        tvAddress.setText(address);
        tvCity.setText(city);
        tvBirthday.setText(birthday);
        tvVip.setText(isVip ? "Sí" : "No");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent intent = new Intent(ClientDetailsActivity.this, ClientEditActivity.class);
            intent.putExtra("dni", getIntent().getStringExtra("dni"));
            startActivity(intent);
            return true;
        }
         if (item.getItemId() == R.id.delete) {
         Intent intent = getIntent();
         String clientDni = intent.getStringExtra("dni");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.clientDao().deleteByDni(clientDni);
        finish();
        return true;
    }
        return super.onOptionsItemSelected(item);
    }
}