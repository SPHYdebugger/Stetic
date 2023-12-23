package com.sphy.stetic.view.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.ClientRegisterContract;
import com.sphy.stetic.presenter.ClientRegisterPresenter;

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
        EditText etAddress = findViewById(R.id.cliente_address);
        EditText etCity = findViewById(R.id.cliente_city);
        EditText etBirthday = findViewById(R.id.cliente_birthday);
        CheckBox checkVip = findViewById(R.id.client_vip);

        String name = etName.getText().toString();
        String lastname = etLastname.getText().toString();
        String dni = etDni.getText().toString();
        String address = etAddress.getText().toString();
        String city = etCity.getText().toString();
        String birthday = etBirthday.getText().toString();
        boolean vip = checkVip.isChecked();

        Client client = new Client(name, lastname, dni, address, city, birthday, vip);
        presenter.insertClient(client);
    }

    @Override
    public void showInsertSuccessMessage() {
        Toast.makeText(this,"Tienda insertada correctamente", Toast.LENGTH_LONG).show();
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
        EditText etAddress = findViewById(R.id.cliente_address);
        EditText etCity = findViewById(R.id.cliente_city);
        EditText etBirthday = findViewById(R.id.cliente_birthday);

        etName.setText("");
        etLastname.setText("");
        etDni.setText("");
        etAddress.setText("");
        etCity.setText("");
        etBirthday.setText("");

        etName.requestFocus();
    }
}
