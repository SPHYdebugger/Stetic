package com.sphy.stetic.contract;

import com.sphy.stetic.Domain.Client;

public interface ClientRegisterContract {

    interface View {
        void showInsertSuccessMessage();

        void showInsertErrorMessage();

        void clearFields();
    }

    interface Presenter {
        void insertClient(Client client);
    }

    interface Model {
        interface OnClientInsertedListener {
            void onClientInsertedSuccess();

            void onClientInsertedError(String message);
        }

        void insertClient(Client client, OnClientInsertedListener listener);
    }
}
