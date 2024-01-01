package com.sphy.stetic.contract.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;

import java.util.List;

public interface ProductSearchContract {
    interface View {
        void displaySearchResults(List<Product> searchResults);

        void displaySearchError(String message);
    }

    interface Presenter {
        void performSearch(int searchId, String searchText);
    }

    interface Model {
        interface OnSearchListener {
            void onSearchSuccess(List<Product> searchResults);

            void onSearchError(String errorMessage);
        }

        void performSearch(int searchId, String searchText, OnSearchListener listener);
    }
}
