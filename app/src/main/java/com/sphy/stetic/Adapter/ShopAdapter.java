package com.sphy.stetic.Adapter;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.Domain.Shop;
import com.sphy.stetic.R;
import com.sphy.stetic.api.ClientApi;
import com.sphy.stetic.api.ClientApiInterface;
import com.sphy.stetic.api.ShopApi;
import com.sphy.stetic.api.ShopApiInterface;
import com.sphy.stetic.view.Shops.ShopDetailsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {

    private List<Shop> shops;


    public ShopAdapter(List<Shop> shops) {
        this.shops = shops;
    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_list_item, parent, false);
        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {
        holder.tvName.setText(shops.get(position).getName());
        holder.tvAddress.setText(shops.get(position).getAddress());
        holder.tvCity.setText(shops.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ShopHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvAddress;
        public TextView tvCity;
        public Button deleteButton;
        public Button detailsButton;
        public View parentView;

        public ShopHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvName = view.findViewById(R.id.shop_item_name);
            tvAddress = view.findViewById(R.id.shop_item_address);
            tvCity = view.findViewById(R.id.shop_item_city);
            deleteButton = view.findViewById(R.id.delete_item_button);
            detailsButton = view.findViewById(R.id.details_item_button);


            detailsButton.setOnClickListener(v -> goShopDetails(view));
            deleteButton.setOnClickListener(v -> deleteShop());


        }


        private void goShopDetails(View itemView) {
        Intent intent = new Intent(itemView.getContext(), ShopDetailsView.class);
        Shop shop = shops.get(getAdapterPosition());
        intent.putExtra("id", shop.getId());
        intent.putExtra("name", shop.getName());
        intent.putExtra("latitude", shop.getLatitude());
        intent.putExtra("longitude", shop.getLongitude());
        itemView.getContext().startActivity(intent);
        }



        private void deleteShop() {
            int currentPosition = getAdapterPosition();
            Shop shop = shops.get(currentPosition);

            ShopApiInterface api = ShopApi.buildInstance();
            Call<Void> deleteShopCall = api.deleteShop(shop.getId());
            deleteShopCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {

                        shops.remove(currentPosition);
                        notifyItemRemoved(currentPosition);
                        notifyItemRangeChanged(currentPosition, shops.size());
                    } else {

                        handleApiResponseError(response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("deleteShop", "Error al conectar con el servidor: " + t.getMessage());
                }
            });
        }

        private void handleApiResponseError(int responseCode) {
            if (responseCode == 500) {
                Toast.makeText(parentView.getContext(), "No se puede eliminar una tienda si tiene empleados asociados. Por favor elimina antes los empleados", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
