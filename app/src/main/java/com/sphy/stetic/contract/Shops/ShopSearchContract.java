package com.sphy.stetic.contract.Shops;

import com.sphy.stetic.Domain.Shop;

import java.util.List;

public interface ShopSearchContract {
    interface View {
        void displaySearchResults(List<Shop> searchResults);

        void displaySearchError(String message);
    }

    interface Presenter {
        void performSearch(int searchId, String searchCity);
    }

    interface Model {
        interface OnSearchListener {
            void onSearchSuccess(List<Shop> searchResults);

            void onSearchError(String errorMessage);
        }

        void performSearch(int searchId, String searchText, OnSearchListener listener);
    }
}
