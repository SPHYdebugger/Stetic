package com.sphy.stetic.presenter.Clients;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientSearchContract;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchPresenter implements ClientSearchContract.Presenter {
    private ClientSearchContract.View view;
    private ClientSearchContract.Model model;

    public ClientSearchPresenter(ClientSearchContract.View view, ClientSearchContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void performSearch(String searchText) {
        List<Client> allClients = model.getAllClients();
        List<Client> searchResults = filterClients(allClients, searchText);
        view.showSearchResults(searchResults);
    }

    private List<Client> filterClients(List<Client> clients, String searchText) {

        searchText = searchText.toLowerCase();
        List<Client> filteredClients = new ArrayList<>();

        for (Client client : clients) {
            if (client.getFirstName().toLowerCase().contains(searchText) ||
                    client.getLastName().toLowerCase().contains(searchText) ||
                    client.getCity().toLowerCase().contains(searchText)) {
                filteredClients.add(client);
            }
        }

        return filteredClients;
    }
}
