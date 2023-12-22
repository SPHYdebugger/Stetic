package com.sphy.stetic.Activity;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;
import com.sphy.stetic.Adapter.ClientAdapter;
import com.sphy.stetic.Db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClientListActivity extends AppCompatActivity {

    private List<Client> clients;
    private ClientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        clients = new ArrayList<>();

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        clients.addAll(db.clientDao().getAll());

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

        clients.clear();
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        clients.addAll(db.clientDao().getAll());

        adapter.notifyDataSetChanged();
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, RegisterClientActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.searchClient){
            Intent intent = new Intent(ClientListActivity.this, SearchClientActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}