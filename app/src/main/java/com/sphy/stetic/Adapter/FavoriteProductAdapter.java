package com.sphy.stetic.Adapter;

import static com.sphy.stetic.Db.ConstantsDb.DATABASE_NAME;

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

import com.sphy.stetic.Db.AppDatabase;
import com.sphy.stetic.Db.ProductDao;
import com.sphy.stetic.Domain.Product;
import com.sphy.stetic.R;
import com.sphy.stetic.api.ProductApi;
import com.sphy.stetic.api.ProductApiInterface;
import com.sphy.stetic.view.Products.ProductDetailsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.ProductHolder> {

    private List<Product> products;

    private ProductDao productDao;
    private AppDatabase appDatabase;

    public FavoriteProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public FavoriteProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        return new FavoriteProductAdapter.ProductHolder(view);
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
            int currentPosition = getAdapterPosition();
            Product product = products.get(currentPosition);

            //AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            //db.productDao().delete(product);

            products.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition, products.size());

            }


        }
    }


