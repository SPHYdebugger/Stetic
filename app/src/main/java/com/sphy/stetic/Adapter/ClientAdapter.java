package com.sphy.stetic.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.Activity.ClientDetailsActivity;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.R;
import com.sphy.stetic.Domain.Client;

import java.util.List;

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
        holder.tvName.setText(clients.get(position).getFirstName());
        holder.tvLastname.setText(clients.get(position).getLastName());
        holder.tvDni.setText(clients.get(position).getDni());

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvLastname;
        public TextView tvDni;
        public Button deleteButton;
        public Button detailsButton;
        public View parentView;

        public TaskHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvName = view.findViewById(R.id.client_item_name);
            tvLastname = view.findViewById(R.id.client_item_lastname);
            tvDni = view.findViewById(R.id.client_item_dni);
            deleteButton = view.findViewById(R.id.delete_item_button);
            detailsButton = view.findViewById(R.id.details_item_button);


            detailsButton.setOnClickListener(v -> goClientDetails(view));
            deleteButton.setOnClickListener(v -> deleteClient(view));


        }


        private void goClientDetails(View itemView) {
        Intent intent = new Intent(itemView.getContext(), ClientDetailsActivity.class);
        Client client = clients.get(getAdapterPosition());
        intent.putExtra("dni", client.getDni());
        itemView.getContext().startActivity(intent);
        }



        private void deleteClient(View itemView) {
            int currentPosition = getAdapterPosition();
            Client client = clients.get(currentPosition);

            AppDatabase db = Room.databaseBuilder(itemView.getContext(), AppDatabase.class, "tasks").allowMainThreadQueries().build();
            db.clientDao().delete(client);

            clients.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition, clients.size());
        }
    }
}
