package com.nilscreation.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nilscreation.ecommerce.CartActivity;
import com.nilscreation.ecommerce.R;
import com.nilscreation.ecommerce.model.CartModel;
import com.nilscreation.ecommerce.service.MyDBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModel> cartlist;
    int mqty;
    float mfinalPrice;

    MyDBHelper myDBHelper;

    public CartAdapter(Context context, ArrayList<CartModel> cartlist) {
        this.context = context;
        this.cartlist = cartlist;
    }

    public void sendDataToActivity(ArrayList<CartModel> dataList) {
        if (context instanceof CartActivity) {
            CartActivity activity = (CartActivity) context;
            activity.receiveDataFromAdapter(dataList);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartModel cartModel = cartlist.get(position);

        Glide.with(context).load(cartModel.getImage()).into(holder.cartImg);
        holder.cartTitle.setText(cartModel.getTitle());
        holder.cartQty.setText(String.valueOf(cartModel.getQty()));
        holder.cartfoodPrice.setText(String.valueOf(cartModel.getItemTotalPrice()));
        Toast.makeText(context, "" + String.valueOf(cartModel.getItemTotalPrice()), Toast.LENGTH_SHORT).show();
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHelper = new MyDBHelper(context);
                myDBHelper.deleteData(cartModel.getTitle());
                cartlist.remove(cartModel);
                notifyDataSetChanged();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
            }
        });

        sendDataToActivity(cartlist);

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mqty = Integer.parseInt(holder.cartQty.getText().toString()) + 1;
                holder.cartQty.setText(String.valueOf(mqty));

                cartModel.setQty(mqty);

                mfinalPrice = mqty * cartModel.getPrice();

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedPrice = decimalFormat.format(mfinalPrice);
                holder.cartfoodPrice.setText(formattedPrice);

                sendDataToActivity(cartlist);
            }
        });

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.cartQty.getText().toString()) > 1) {
                    mqty = Integer.parseInt(holder.cartQty.getText().toString()) - 1;
                    holder.cartQty.setText(String.valueOf(mqty));

                    cartModel.setQty(mqty);

                    mfinalPrice = mqty * cartModel.getPrice();

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String formattedPrice = decimalFormat.format(mfinalPrice);
                    holder.cartfoodPrice.setText(formattedPrice);
                    sendDataToActivity(cartlist);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartImg, plusBtn, minusBtn, deleteItem;
        TextView cartTitle, cartQty, cartfoodPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImg = itemView.findViewById(R.id.cartImg);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            cartTitle = itemView.findViewById(R.id.cartfoodTitle);
            cartQty = itemView.findViewById(R.id.cartQty);
            cartfoodPrice = itemView.findViewById(R.id.cartfoodPrice);
            deleteItem = itemView.findViewById(R.id.deleteItem);
        }
    }
}
