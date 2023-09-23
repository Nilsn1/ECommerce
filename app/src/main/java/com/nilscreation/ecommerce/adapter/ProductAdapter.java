package com.nilscreation.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nilscreation.ecommerce.DetailActivity;
import com.nilscreation.ecommerce.R;
import com.nilscreation.ecommerce.model.ProductDetails;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewholder> {

    Context context;
    List<ProductDetails> productList;

    public ProductAdapter(Context context, List<ProductDetails> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.viewholder holder, int position) {

        ProductDetails product = productList.get(position);
        Glide.with(context).load(product.getImage()).into(holder.imageview);
        holder.txtTitle.setText(product.getTitle());
        holder.txtCategory.setText(product.getCategory());
        holder.txtPrice.setText("" + product.getPrice());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Id", product.getId());
                intent.putExtra("Title", product.getTitle());
                intent.putExtra("Description", product.getDescription());
                intent.putExtra("Price", product.getPrice());
                intent.putExtra("Url", product.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        ImageView imageview;
        TextView txtTitle, txtCategory, txtPrice;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            imageview = itemView.findViewById(R.id.imageview);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
