package com.sphy.stetic.contract;

import com.sphy.stetic.Domain.Client;

import java.util.List;

public interface ClientSearchContract {
    interface View {
        void showSearchResults(List<Client> searchResults);
    }

    interface Presenter {
        void performSearch(String searchText);
    }

    interface Model {
        List<Client> getAllClients();
    }
}

