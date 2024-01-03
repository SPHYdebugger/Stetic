package com.sphy.stetic.contract.Clients;

import com.sphy.stetic.Domain.Client;

public interface ClientDetailsContract {
    interface View {
        void displayClientDetails(Client client);
        void showUpdateSuccessMessage();
        void showUpdateErrorMessage();
        void showDeleteSuccessMessage();
        void showDeleteErrorMessage();
    }

    interface Presenter {
        void getClientDetails(long clientId);

        void deleteClient(String dni);
    }

    interface Model {
        interface OnClientDetailsListener {
            void onClientDetailsSuccess(Client client);
            void onClientDetailsError(String message);
        }

        interface OnUpdateListener {
            void onUpdateSuccess();
            void onUpdateError(String message);
        }

        interface OnDeleteListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void getClientDetails(long clientId, OnClientDetailsListener listener);

        void deleteClient(String dni, OnDeleteListener listener);
    }
}