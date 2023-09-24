package com.nilscreation.ecommerce.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nilscreation.ecommerce.R;
import com.nilscreation.ecommerce.adapter.CartAdapter;
import com.nilscreation.ecommerce.model.CartModel;
import com.nilscreation.ecommerce.service.MyDBHelper;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    TextView itemTotalPrice, deliveryCharges, totalCharges;
    Button checkout, btnAdd;
    RecyclerView recyclerviewCart;
    ArrayList<CartModel> cartlist;
    ArrayList<CartModel> dataList;
    CartAdapter cartAdapter;
    int mdeliveryCharges, orderPriceFinal;
    LinearLayout emptyCart, cartlayout;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemTotalPrice = findViewById(R.id.itemTotalPrice);
        deliveryCharges = findViewById(R.id.deliveryCharges);
        totalCharges = findViewById(R.id.totalCharges);
        emptyCart = findViewById(R.id.emptyCart);
        cartlayout = findViewById(R.id.cartlayout);
        checkout = findViewById(R.id.checkout);
        btnAdd = findViewById(R.id.btnAdd);

        myDBHelper = new MyDBHelper(CartActivity.this);

        recyclerviewCart = findViewById(R.id.recyclerviewCart);
        recyclerviewCart.setLayoutManager(new LinearLayoutManager(this));
        cartlist = new ArrayList<>();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                intent.putExtra("Total", orderPriceFinal);
                myDBHelper.deleteAllData();
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void receiveDataFromAdapter(ArrayList<CartModel> dataList) {

        int totalPrice = 0;

        this.dataList = dataList;

        for (CartModel product : dataList) {
            totalPrice += product.getPrice() * product.getQty();
            mdeliveryCharges = 10;
        }
        orderPriceFinal = totalPrice + mdeliveryCharges;

        itemTotalPrice.setText("$" + totalPrice);
        deliveryCharges.setText("$" + mdeliveryCharges);
        totalCharges.setText("$" + orderPriceFinal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        cartlist = myDBHelper.readData();

        if (!cartlist.isEmpty()) {
            cartAdapter = new CartAdapter(CartActivity.this, cartlist);
            recyclerviewCart.setAdapter(cartAdapter);
            emptyCart.setVisibility(View.GONE);
            cartlayout.setVisibility(View.VISIBLE);
            recyclerviewCart.setVisibility(View.VISIBLE);
        } else {
            emptyCart.setVisibility(View.VISIBLE);
            cartlayout.setVisibility(View.GONE);
            recyclerviewCart.setVisibility(View.GONE);
        }
    }
}