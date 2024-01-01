package com.sphy.stetic.view.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.Adapter.ClientAdapter;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.R;

import java.util.ArrayList;
import java.util.List;

public class SearchClientView extends AppCompatActivity {

    private List<Client> allClients;
    private List<Client> searchResults;
    private ClientAdapter adapter;
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_client);


        allClients = new ArrayList<>();
        searchResults = new ArrayList<>();

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        allClients.addAll(db.clientDao().getAll());
        Log.d("SearchClientActivity", "Total clients: " + allClients.size());

        RecyclerView recyclerView = findViewById(R.id.search_results_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ClientAdapter(searchResults);
        recyclerView.setAdapter(adapter);

        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String searchText = searchEditText.getText().toString().trim().toLowerCase();
        searchResults.clear();

        for (Client client : allClients) {
            if (client.getFirstName().toLowerCase().contains(searchText) ||
                    client.getLastName().toLowerCase().contains(searchText) ||
                    client.getCity().toLowerCase().contains(searchText)) {
                searchResults.add(client);
            }
        }

        adapter.notifyDataSetChanged();
    }


    public void performSearch(View view) {
    }
}

