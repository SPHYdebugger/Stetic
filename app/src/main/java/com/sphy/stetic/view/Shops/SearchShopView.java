package com.sphy.stetic.view.Shops;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sphy.stetic.Adapter.ShopAdapter;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Shops.ShopSearchContract;
import com.sphy.stetic.presenter.Shops.ShopSearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchShopView extends AppCompatActivity implements ShopSearchContract.View {

    private List<Shop> searchResults;
    private ShopAdapter adapter;
    private EditText searchEditText;
    private Button searchButton;
    private ShopSearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shop);

        searchResults = new ArrayList<>();
        adapter = new ShopAdapter(searchResults);

        RecyclerView recyclerView = findViewById(R.id.search_results_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);

        presenter = new ShopSearchPresenter(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String searchCity = searchEditText.getText().toString().trim().toLowerCase();
        int searchId = 0;


        try {
            searchId = Integer.parseInt(searchCity);
            searchCity = "";
        } catch (NumberFormatException e) {
            searchId = 0;
            searchCity = searchEditText.getText().toString().trim().toLowerCase();
        }



        presenter.performSearch(searchId, searchCity);
    }


    @Override
    public void displaySearchResults(List<Shop> searchResults) {
        this.searchResults.clear();
        this.searchResults.addAll(searchResults);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displaySearchError(String message) {
        Toast.makeText(this, "Error de búsqueda: 5" + message, Toast.LENGTH_SHORT).show();
    }
}
