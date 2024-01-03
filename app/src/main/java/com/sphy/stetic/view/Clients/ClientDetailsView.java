package com.sphy.stetic.view.Clients;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientDetailsContract;
import com.sphy.stetic.presenter.Clients.ClientDetailsPresenter;

public class ClientDetailsView extends AppCompatActivity implements ClientDetailsContract.View {

    private TextView tvClientId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvDni;

    private TextView tvCity;

    private TextView tvVip;
    private boolean isVip;



    private ClientDetailsContract.Presenter presenter;
    private long id;
    private String dni;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        tvClientId = findViewById(R.id.detail_clientId);
        tvFirstName = findViewById(R.id.detail_firstName);
        tvLastName = findViewById(R.id.detail_lastName);
        tvDni = findViewById(R.id.detail_dni);
        tvCity = findViewById(R.id.detail_city);
        tvVip = findViewById(R.id.detail_vip);


        presenter = new ClientDetailsPresenter(this);

        Intent intent = getIntent();
        id = intent.getLongExtra("id",id);
        dni = intent.getStringExtra("dni");
        presenter.getClientDetails(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        id = intent.getLongExtra("id",id);
        dni = intent.getStringExtra("dni");
        presenter.getClientDetails(id);
    }

    @Override
    public void displayClientDetails(Client client) {
        tvClientId.setText(String.valueOf(client.getId()));
        tvFirstName.setText(client.getFirstname());
        tvLastName.setText(client.getLastname());
        tvDni.setText(client.getDni());

        tvCity.setText(client.getCity());

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
        finish();
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
            intent.putExtra("id", id);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.delete) {
            presenter.deleteClient(dni);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}