package com.sphy.stetic.presenter.Products;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.contract.Clients.ClientSearchContract;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchPresenter implements ClientSearchContract.Presenter {
    private ClientSearchContract.View view;
    private ClientSearchContract.Model model;

    public ProductSearchPresenter(ClientSearchContract.View view, ClientSearchContract.Model model) {
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
        // Implementa la lógica de filtrado según tus necesidades
        // Por ejemplo, podrías buscar coincidencias en el nombre, apellido o ciudad
        // y devolver la lista de resultados filtrados.
        // Aquí, se proporciona una implementación simple que busca coincidencias en el nombre.
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
