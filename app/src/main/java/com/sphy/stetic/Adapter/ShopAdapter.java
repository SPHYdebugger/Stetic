package com.sphy.stetic.Adapter;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.Activity.Clients.ClientDetailsActivity;
import com.sphy.stetic.Activity.Shops.ShopDetailsActivity;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.TaskHolder> {

    private List<Shop> shops;


    public ShopAdapter(List<Shop> shops) {
        this.shops = shops;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_list_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.tvName.setText(shops.get(position).getName());
        holder.tvAddress.setText(shops.get(position).getAddress());
        holder.tvCity.setText(shops.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvAddress;
        public TextView tvCity;
        public Button deleteButton;
        public Button detailsButton;
        public View parentView;

        public TaskHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvName = view.findViewById(R.id.shop_item_name);
            tvAddress = view.findViewById(R.id.shop_item_address);
            tvCity = view.findViewById(R.id.shop_item_city);
            deleteButton = view.findViewById(R.id.delete_item_button);
            detailsButton = view.findViewById(R.id.details_item_button);


            detailsButton.setOnClickListener(v -> goShopDetails(view));
            deleteButton.setOnClickListener(v -> deleteShop(view));


        }


        private void goShopDetails(View itemView) {
        Intent intent = new Intent(itemView.getContext(), ShopDetailsActivity.class);
        Shop shop = shops.get(getAdapterPosition());
        intent.putExtra("name", shop.getName());
        itemView.getContext().startActivity(intent);
        }



        private void deleteShop(View itemView) {
            int currentPosition = getAdapterPosition();
            Shop shop = shops.get(currentPosition);

            AppDatabase db = Room.databaseBuilder(itemView.getContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            db.shopDao().delete(shop);

            shops.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition, shops.size());
        }
    }
}