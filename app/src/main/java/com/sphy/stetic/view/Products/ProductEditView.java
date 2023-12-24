package com.sphy.stetic.view.Products;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientEditContract;
import com.sphy.stetic.presenter.Clients.ClientEditPresenter;

public class ProductEditView extends AppCompatActivity implements ClientEditContract.View {
    private Client client;
    private ClientEditContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);

        Intent intent = getIntent();
        String clientDni = intent.getStringExtra("dni");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        client = db.clientDao().findByDni(clientDni);
        loadClient(client);

        presenter = new ClientEditPresenter(this);

        Button cancelButton = findViewById(R.id.btnCancel);
        Button modifyButton = findViewById(R.id.btnModify);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancelEditing();
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyClient();
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
        CheckBox etVip = findViewById(R.id.client_vip);


        etFirstName.setText(client.getFirstName());
        etLastName.setText(client.getLastName());
        etDni.setText(client.getDni());
        etAddress.setText(client.getAddress());
        etCity.setText(client.getCity());
        etBirthday.setText(client.getBirthDay());
        etVip.setChecked(client.isVip());
    }

    private void modifyClient() {
        EditText etFirstName = findViewById(R.id.edit_firstName);
        EditText etLastName = findViewById(R.id.edit_lastName);
        EditText etDni = findViewById(R.id.edit_dni);
        EditText etAddress = findViewById(R.id.edit_address);
        EditText etCity = findViewById(R.id.edit_city);
        EditText etBirthday = findViewById(R.id.edit_birthday);
        CheckBox etVip = findViewById(R.id.client_vip);

        client.setFirstName(etFirstName.getText().toString());
        client.setLastName(etLastName.getText().toString());
        client.setDni(etDni.getText().toString());
        client.setAddress(etAddress.getText().toString());
        client.setCity(etCity.getText().toString());
        client.setBirthDay(etBirthday.getText().toString());
        client.setVip(etVip.isChecked());

        // Actualizar el cliente en la base de datos
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.clientDao().update(client);


        finish();
    }

    @Override
    public void showUpdateSuccessMessage() {

    }

    @Override
    public void showUpdateErrorMessage() {

    }

    @Override
    public void showCancelMessage() {

    }

    @Override
    public void clearFields() {

    }
}
