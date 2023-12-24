package com.sphy.stetic.contract.Clients;

import com.sphy.stetic.Domain.Client;

public interface ClientEditContract {
    interface View {
        void showUpdateSuccessMessage();

        void showUpdateErrorMessage();

        void showCancelMessage();

        void clearFields();
    }

    interface Presenter {
        void updateClient(Client client);

        void cancelEditing();
    }

    interface Model {
        interface OnUpdateClientListener {
            void onUpdateClientSuccess();

            void onUpdateClientError(String message);
        }

        void updateClient(Client client, OnUpdateClientListener listener);
    }
}


