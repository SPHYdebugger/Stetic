package com.sphy.stetic.contract.Clients;

import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Product;

public interface ClientEditContract {
    interface View {
        void showUpdateSuccessMessage();
        void showUpdateErrorMessage();
        void displayClientDetails(Client client);
    }

    interface Presenter {
        void getClientDetails(long id);

        void updateClient(long id, Client client);


    }

    interface Model {
        void getClientDetails(long id, OnClientDetailsListener listener);


        interface OnUpdateClientListener {
            void onUpdateClientSuccess(String successMessage);

            void onUpdateClientError(String errorMessage);
        }
        interface OnClientDetailsListener {
            void onClientDetailsSuccess(Client client);
            void onClientDetailsError(String errorMessage);
        }


        void updateClient(long id, Client client, OnUpdateClientListener listener);

    }



}
