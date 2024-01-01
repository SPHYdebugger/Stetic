package com.sphy.stetic.view.Products;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sphy.stetic.Adapter.ClientAdapter;
import com.sphy.stetic.Adapter.ProductAdapter;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.contract.Products.ProductSearchContract;
import com.sphy.stetic.presenter.Products.ProductSearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchProductView extends AppCompatActivity implements ProductSearchContract.View {

    private List<Product> searchResults;
    private ProductAdapter adapter;
    private EditText searchEditText;
    private Button searchButton;
    private ProductSearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        searchResults = new ArrayList<>();
        adapter = new ProductAdapter(searchResults);

        RecyclerView recyclerView = findViewById(R.id.search_results_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);

        presenter = new ProductSearchPresenter(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String searchName = searchEditText.getText().toString().trim().toLowerCase();
        int searchId = 0;


        try {
            searchId = Integer.parseInt(searchName);
            searchName = "";
        } catch (NumberFormatException e) {
            searchId = 0;
        }



        presenter.performSearch(searchId, searchName);
    }


    @Override
    public void displaySearchResults(List<Product> searchResults) {
        this.searchResults.clear();
        this.searchResults.addAll(searchResults);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displaySearchError(String message) {
        Toast.makeText(this, "Error de búsqueda: " + message, Toast.LENGTH_SHORT).show();
    }
}
