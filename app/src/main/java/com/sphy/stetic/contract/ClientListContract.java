package com.sphy.stetic.contract;

import com.sphy.stetic.Domain.Client;

import java.util.List;

public interface ClientListContract {

    interface View{
        void listClients(List<Client> clients);
        void showMessage(String message);
    }
    interface Presenter{
        void loadAllClients();
    }
    interface Model{
        interface OnLoadClientsListener{
            void onLoadClientsSuccess(List<Client> clients);
            void onLoadClientsError(String message);
        }
        void loadAllClients(OnLoadClientsListener listener);
    }

}
