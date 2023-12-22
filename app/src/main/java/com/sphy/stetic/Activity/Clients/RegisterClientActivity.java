package com.sphy.stetic.Activity.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;

public class RegisterClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
    }

    public void createTask(View view) {
        EditText etName = findViewById(R.id.client_firstName);
        EditText etLastname = findViewById(R.id.client_lastName);
        EditText etDni = findViewById(R.id.client_dni);
        EditText etAddress = findViewById(R.id.cliente_address);
        EditText etCity = findViewById(R.id.cliente_city);
        EditText etBirthday = findViewById(R.id.cliente_birthday);
        CheckBox checkVip = findViewById(R.id.client_vip);

        String taskName = etName.getText().toString();
        String taskLastname = etLastname.getText().toString();
        String taskDni = etDni.getText().toString();
        String taskAddress = etAddress.getText().toString();
        String taskCity = etCity.getText().toString();
        String taskBirthday = etBirthday.getText().toString();
        boolean vip = checkVip.isChecked();

        Client client = new Client(taskName, taskLastname,taskDni,taskAddress,taskCity,taskBirthday, vip);
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        db.clientDao().insert(client);

        Toast.makeText(this, R.string.client_insert, Toast.LENGTH_LONG).show();

        etName.setText("");
        etLastname.setText("");
        etDni.setText("");
        etAddress.setText("");
        etCity.setText("");
        etBirthday.setText("");


        etName.requestFocus();
    }
}