package com.sphy.stetic.Adapter;

import static com.sphy.stetic.Util.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.view.Clients.ClientDetailsView;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.R;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.view.Products.ProductDetailsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> products;


    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item2, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        String idString = String.valueOf(products.get(position).getId());
        holder.tvId.setText(idString);
        holder.tvName.setText(products.get(position).getName());
        holder.tvDescription.setText(products.get(position).getDescription());
        holder.tvPrice.setText(String.valueOf(products.get(position).getPrice()));




    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        public TextView tvId;
        public TextView tvName;
        public TextView tvDescription;

        public TextView tvPrice;

        public Button deleteButton;
        public Button detailsButton;
        public View parentView;

        public ProductHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvId = view.findViewById(R.id.product_item_id);
            tvName = view.findViewById(R.id.product_item_name);
            tvDescription = view.findViewById(R.id.product_item_description);
            tvPrice = view.findViewById(R.id.product_item_price);

            deleteButton = view.findViewById(R.id.delete_item_button);
            detailsButton = view.findViewById(R.id.details_item_button);



            detailsButton.setOnClickListener(v -> goProductDetails(view));
            deleteButton.setOnClickListener(v -> deleteProduct());



        }


        private void goProductDetails(View itemView) {
            Intent intent = new Intent(itemView.getContext(), ProductDetailsView.class);
            Product product = products.get(getAdapterPosition());
            intent.putExtra("id", product.getId());
            itemView.getContext().startActivity(intent);
        }

        private void deleteProduct() {

            AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
            builder.setTitle(parentView.getResources().getString(R.string.confirmation));
            builder.setMessage(parentView.getResources().getString(R.string.delete_product_alert));

            //Boton confirmar
            builder.setPositiveButton(parentView.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteProductConfirmed();
                }
            });

            //boton cancelar
            builder.setNegativeButton(parentView.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            //Mostrar el aviso
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private void deleteProductConfirmed() {
            int currentPosition = getAdapterPosition();
            Product product = products.get(currentPosition);

            ProductApiInterface api = ProductApi.buildInstance();
            Call<Void> deleteProductCall = api.deleteProduct(product.getId());
            deleteProductCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                        products.remove(currentPosition);
                        notifyItemRemoved(currentPosition);
                        notifyItemRangeChanged(currentPosition, products.size());

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("deleteProduct", "Error al conectar con el servidor: " + t.getMessage());

                }
            });


        }


    }
}
