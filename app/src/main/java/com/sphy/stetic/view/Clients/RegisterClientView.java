package com.sphy.stetic.view.Clients;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientRegisterContract;
import com.sphy.stetic.presenter.Clients.ClientRegisterPresenter;

public class RegisterClientView extends AppCompatActivity implements ClientRegisterContract.View {

    private ClientRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        presenter = new ClientRegisterPresenter(this);
    }

    public void createClient(View view) {
        EditText etName = findViewById(R.id.client_firstName);
        EditText etLastname = findViewById(R.id.client_lastName);
        EditText etDni = findViewById(R.id.client_dni);
        EditText etCity = findViewById(R.id.cliente_city);
        CheckBox checkVip = findViewById(R.id.client_vip);

        String name = etName.getText().toString();
        String lastname = etLastname.getText().toString();
        String dni = etDni.getText().toString();
        String city = etCity.getText().toString();
        boolean vip = checkVip.isChecked();

        Client client = new Client(name, lastname, dni, city, vip);
        presenter.insertClient(client);
    }

    @Override
    public void showInsertSuccessMessage() {
        Toast.makeText(this,R.string.client_successful_added, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInsertErrorMessage() {
        Toast.makeText(this, "Error al insertar el cliente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearFields() {
        EditText etName = findViewById(R.id.client_firstName);
        EditText etLastname = findViewById(R.id.client_lastName);
        EditText etDni = findViewById(R.id.client_dni);
        EditText etCity = findViewById(R.id.cliente_city);


        etName.setText("");
        etLastname.setText("");
        etDni.setText("");
        etCity.setText("");

        etName.requestFocus();
    }
}
