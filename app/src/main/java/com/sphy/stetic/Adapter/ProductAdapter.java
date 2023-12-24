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

import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.view.Clients.ClientDetailsView;
import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.R;
import com.sphy.stetic.Domain.Client;
import com.sphy.stetic.view.Products.ProductDetailsView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.TaskHolder> {

    private List<Product> products;


    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.tvId.setText(products.get(position).getId());
        holder.tvName.setText(products.get(position).getName());
        holder.tvPrice.setText(String.valueOf(products.get(position).getPrice()));



    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {

        public TextView tvId;
        public TextView tvName;

        public TextView tvPrice;

        public Button deleteButton;
        public Button detailsButton;
        public View parentView;

        public TaskHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvId = view.findViewById(R.id.product_item_id);
            tvName = view.findViewById(R.id.product_item_name);

            tvPrice = view.findViewById(R.id.product_item_price);

            deleteButton = view.findViewById(R.id.delete_item_button);
            detailsButton = view.findViewById(R.id.details_item_button);


            detailsButton.setOnClickListener(v -> goProductDetails(view));
            deleteButton.setOnClickListener(v -> deleteProduct(view));


        }


        private void goProductDetails(View itemView) {
            Intent intent = new Intent(itemView.getContext(), ProductDetailsView.class);
            Product product = products.get(getAdapterPosition());
            intent.putExtra("id", product.getId());
            itemView.getContext().startActivity(intent);
        }



        private void deleteProduct(View itemView) {
            int currentPosition = getAdapterPosition();
            Product product = products.get(currentPosition);

            AppDatabase db = Room.databaseBuilder(itemView.getContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            db.productDao().delete(product);

            products.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition, products.size());
        }
    }
}
