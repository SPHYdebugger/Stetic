package com.sphy.stetic.view.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;

public class ClientEditView extends AppCompatActivity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);

        Intent intent = getIntent();
        String clientDni = intent.getStringExtra("dni");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        client = db.clientDao().findByDni(clientDni);
        loadClient(client);

        Button cancelButton = findViewById(R.id.btnCancel);
        Button modifyButton = findViewById(R.id.btnModify);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyClient();
                Intent intent = new Intent(ClientEditView.this, ClientListView.class);
                startActivity(intent);
            }
        });
    }

    private void loadClient(Client client) {
        EditText etFirstName = findViewById(R.id.edit_firstName);
        EditText etLastName = findViewById(R.id.edit_lastName);
        EditText etDni = findViewById(R.id.edit_dni);
        EditText etAddress = findViewById(R.id.edit_address);
        EditText etCity = findViewById(R.id.edit_city);
        EditText etBirthday = findViewById(R.id.edit_birthday);
        EditText etVip = findViewById(R.id.edit_vip);

        etFirstName.setText(client.getFirstName());
        etLastName.setText(client.getLastName());
        etDni.setText(client.getDni());
        etAddress.setText(client.getAddress());
        etCity.setText(client.getCity());
        etBirthday.setText(client.getBirthDay());
        etVip.setText(String.valueOf(client.isVip()));
    }

    private void modifyClient() {
        EditText etFirstName = findViewById(R.id.edit_firstName);
        EditText etLastName = findViewById(R.id.edit_lastName);
        EditText etDni = findViewById(R.id.edit_dni);
        EditText etAddress = findViewById(R.id.edit_address);
        EditText etCity = findViewById(R.id.edit_city);
        EditText etBirthday = findViewById(R.id.edit_birthday);
        EditText etVip = findViewById(R.id.edit_vip);

        client.setFirstName(etFirstName.getText().toString());
        client.setLastName(etLastName.getText().toString());
        client.setDni(etDni.getText().toString());
        client.setAddress(etAddress.getText().toString());
        client.setCity(etCity.getText().toString());
        client.setBirthDay(etBirthday.getText().toString());
        client.setVip(Boolean.parseBoolean(etVip.getText().toString()));

        // Actualizar el cliente en la base de datos
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.clientDao().update(client);

        // Puedes agregar un mensaje de éxito o realizar otras acciones después de la modificación

        // Cerrar la actividad después de la modificación
        finish();
    }
}
