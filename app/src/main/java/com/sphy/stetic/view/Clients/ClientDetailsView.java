package com.sphy.stetic.view.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.ClientDetailsContract;
import com.sphy.stetic.presenter.ClientDetailsPresenter;

public class ClientDetailsView extends AppCompatActivity implements ClientDetailsContract.View {

    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvDni;
    private TextView tvAddress;
    private TextView tvCity;
    private TextView tvBirthday;
    private TextView tvVip;
    private boolean isVip;



    private ClientDetailsContract.Presenter presenter;
    private String clientDni;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        tvFirstName = findViewById(R.id.detail_firstName);
        tvLastName = findViewById(R.id.detail_lastName);
        tvDni = findViewById(R.id.detail_dni);
        tvAddress = findViewById(R.id.detail_address);
        tvCity = findViewById(R.id.detail_city);
        tvBirthday = findViewById(R.id.detail_birthday);
        tvVip = findViewById(R.id.detail_vip);


        presenter = new ClientDetailsPresenter(this);

        Intent intent = getIntent();
        clientDni = intent.getStringExtra("dni");
        presenter.getClientDetails(clientDni);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        clientDni = intent.getStringExtra("dni");
        presenter.getClientDetails(clientDni);
    }

    @Override
    public void displayClientDetails(Client client) {
        tvFirstName.setText(client.getFirstName());
        tvLastName.setText(client.getLastName());
        tvDni.setText(client.getDni());
        tvAddress.setText(client.getAddress());
        tvCity.setText(client.getCity());
        tvBirthday.setText(client.getBirthDay());
        isVip = client.isVip();
        tvVip.setText(isVip ? "Sí" : "No");

    }

    @Override
    public void showUpdateSuccessMessage() {
        Toast.makeText(this, "Cliente actualizado correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateErrorMessage() {
        Toast.makeText(this, "Error al actualizar el cliente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDeleteSuccessMessage() {
        Toast.makeText(this, "Cliente eliminado correctamente", Toast.LENGTH_LONG).show();
        finish(); // Cerrar la actividad después de eliminar el cliente
    }

    @Override
    public void showDeleteErrorMessage() {
        Toast.makeText(this, "Error al eliminar el cliente", Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent intent = new Intent(ClientDetailsView.this, ClientEditView.class);
            intent.putExtra("dni", clientDni);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.delete) {
            presenter.deleteClient(clientDni);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}