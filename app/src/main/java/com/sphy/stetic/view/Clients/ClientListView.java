package com.sphy.stetic.view.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.Adapter.ClientAdapter;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.contract.ClientListContract;
import com.sphy.stetic.presenter.ClientListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClientListView extends AppCompatActivity implements ClientListContract.View {

    private List<Client> clients;
    private ClientAdapter adapter;
    private ClientListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        presenter = new ClientListPresenter(this);


        clients = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.client_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ClientAdapter(clients);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllClients();


    }

    public void addTask(View view) {
        Intent intent = new Intent(this, RegisterClientView.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search){
            Intent intent = new Intent(ClientListView.this, ClientSearchView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listClients(List<Client> clients) {
        this.clients.clear();
        this.clients.addAll(clients);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}