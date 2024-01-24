package com.sphy.stetic.view.Clients;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Clients.ClientEditContract;
import com.sphy.stetic.contract.Products.ProductEditContract;
import com.sphy.stetic.presenter.Clients.ClientEditPresenter;
import com.sphy.stetic.presenter.Products.ProductEditPresenter;

public class ClientEditView extends AppCompatActivity implements ClientEditContract.View {
    private ClientEditContract.Presenter presenter;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);

        presenter = new ClientEditPresenter(this);


        id = getIntent().getLongExtra("id", id);
        presenter.getClientDetails(id);

        Button modifyButton = findViewById(R.id.btnModify);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyClient();
            }
        });
    }

    @Override
    public void displayClientDetails(Client client) {
        EditText etId = findViewById(R.id.edit_id);
        EditText etFirstname = findViewById(R.id.edit_firstName);
        EditText etLastname = findViewById(R.id.edit_lastName);
        EditText etDni = findViewById(R.id.edit_dni);
        EditText etCity = findViewById(R.id.edit_city);


        etId.setText(String.valueOf(client.getId()));
        etFirstname.setText(client.getFirstname());
        etDni.setText(client.getDni());
        etLastname.setText(client.getLastname());
        etCity.setText(client.getCity());

    }



    private void modifyClient() {

        EditText etFirstname = findViewById(R.id.edit_firstName);
        EditText etLastname = findViewById(R.id.edit_lastName);
        EditText etDni = findViewById(R.id.edit_dni);
        EditText etCity = findViewById(R.id.edit_city);

        Client updatedClient = new Client();

        updatedClient.setFirstname(etFirstname.getText().toString());
        updatedClient.setLastname(etLastname.getText().toString());
        updatedClient.setDni(etDni.getText().toString());
        updatedClient.setCity(etCity.getText().toString());



        presenter.updateClient(id, updatedClient);


    }
    @Override
    public void showUpdateSuccessMessage() {
        Toast.makeText(this, R.string.client_successful_modified, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ClientDetailsView.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUpdateErrorMessage() {
        Toast.makeText(this, R.string.client_successful_modified, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ClientDetailsView.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
