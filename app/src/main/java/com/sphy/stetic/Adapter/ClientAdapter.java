package com.sphy.stetic.Adapter;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.api.ClientApi;
import com.sphy.stetic.api.ClientApiInterface;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.view.Clients.ClientDetailsView;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.R;
import com.sphy.stetic.Domain.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.TaskHolder> {

    private List<Client> clients;


    public ClientAdapter(List<Client> clients) {
        this.clients = clients;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_list_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        String idString = String.valueOf(clients.get(position).getId());
        holder.tvId.setText(idString);
        holder.tvFirstname.setText(clients.get(position).getFirstname());
        holder.tvLastname.setText(clients.get(position).getLastname());
        holder.tvDni.setText(clients.get(position).getDni());


    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {

        public TextView tvId;
        public TextView tvFirstname;
        public TextView tvLastname;
        public TextView tvDni;
        public Button deleteButton;
        public Button detailsButton;
        public View parentView;

        public TaskHolder(@NonNull View view) {
            super(view);
            parentView = view;
            tvId = view.findViewById(R.id.client_item_id);
            tvFirstname = view.findViewById(R.id.client_item_name);
            tvLastname = view.findViewById(R.id.client_item_lastname);
            tvDni = view.findViewById(R.id.client_item_dni);
            deleteButton = view.findViewById(R.id.delete_item_button);
            detailsButton = view.findViewById(R.id.details_item_button);


            detailsButton.setOnClickListener(v -> goClientDetails(view));
            deleteButton.setOnClickListener(v -> deleteClient());


        }


        private void goClientDetails(View itemView) {
        Intent intent = new Intent(itemView.getContext(), ClientDetailsView.class);
        Client client = clients.get(getAdapterPosition());
        intent.putExtra("id", client.getId());
        itemView.getContext().startActivity(intent);
        }



        private void deleteClient() {
            int currentPosition = getAdapterPosition();
            Client client = clients.get(currentPosition);

            ClientApiInterface api = ClientApi.buildInstance();
            Call<Void> deleteClientCall = api.deleteClient(client.getDni());
            deleteClientCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    clients.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, clients.size());

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("deleteClient", "Error al conectar con el servidor: " + t.getMessage());

                }
            });
        }
    }
}
