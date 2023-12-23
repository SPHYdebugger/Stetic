package com.sphy.stetic.contract;

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
        void getClientDetails(String dni);
        void updateClient(Client client);
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

        void getClientDetails(String dni, OnClientDetailsListener listener);
        void updateClient(Client client, OnUpdateListener listener);
        void deleteClient(String dni, OnDeleteListener listener);
    }
}