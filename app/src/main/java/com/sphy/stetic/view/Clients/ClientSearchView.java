package com.sphy.stetic.view.Clients;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.os.Bundle;
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

import com.sphy.stetic.contract.ClientSearchContract;
import com.sphy.stetic.model.ClientSearchModel;
import com.sphy.stetic.presenter.ClientSearchPresenter;

public class ClientSearchView extends AppCompatActivity implements ClientSearchContract.View {
    private List<Client> searchResults;
    private ClientAdapter adapter;
    private EditText searchEditText;
    private Button searchButton;
    private ClientSearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_client);

        searchResults = new ArrayList<>();
        presenter = new ClientSearchPresenter(this, new ClientSearchModel(Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build()));

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
                presenter.performSearch(searchEditText.getText().toString().trim());
            }
        });
    }

    @Override
    public void showSearchResults(List<Client> searchResults) {
        this.searchResults.clear();
        this.searchResults.addAll(searchResults);
        adapter.notifyDataSetChanged();
    }
}
