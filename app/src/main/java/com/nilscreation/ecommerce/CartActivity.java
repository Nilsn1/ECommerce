package com.nilscreation.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nilscreation.ecommerce.adapter.CartAdapter;
import com.nilscreation.ecommerce.model.CartModel;
import com.nilscreation.ecommerce.service.MyDBHelper;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    TextView itemTotalPrice, deliveryCharges, totalCharges, txtAddress;
    Button checkout, btnAdd;
    RecyclerView recyclerviewCart;
    ArrayList<CartModel> cartlist;
    ArrayList<CartModel> dataList;
    CartAdapter cartAdapter;
    String mtitle, mCategory, mimageUrl;
    int mprice, singleItemTotalPrice, mdeliveryCharges, qtyNumber;

    LinearLayout emptyCart, cartlayout;

    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        emptyCart = findViewById(R.id.emptyCart);
        cartlayout = findViewById(R.id.cartlayout);
        checkout = findViewById(R.id.checkout);
        btnAdd = findViewById(R.id.btnAdd);

        myDBHelper = new MyDBHelper(CartActivity.this);

        recyclerviewCart = findViewById(R.id.recyclerviewCart);
        recyclerviewCart.setLayoutManager(new LinearLayoutManager(this));
        cartlist = new ArrayList<>();

        loadData();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
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

//        int totalPrice = 0;
//
//        this.dataList = dataList;
//
//        for (CartModel product : dataList) {
//            totalPrice += product.getPrice() * product.getQty();
//            mdeliveryCharges = 50;
//        }
//        int orderPrice = totalPrice + mdeliveryCharges;
//
//        itemTotalPrice.setText("₹ " + totalPrice);
//        deliveryCharges.setText("₹ " + mdeliveryCharges);
//        totalCharges.setText("₹ " + orderPrice);
    }

    private void loadData() {
        cartlist = myDBHelper.readData();

//        Toast.makeText(this, " " + cartlist.get(0).getTitle(), Toast.LENGTH_SHORT).show();

        if (!cartlist.isEmpty()) {
            cartAdapter = new CartAdapter(CartActivity.this, cartlist);
            recyclerviewCart.setAdapter(cartAdapter);
            emptyCart.setVisibility(View.GONE);
            cartlayout.setVisibility(View.VISIBLE);
        } else {
            emptyCart.setVisibility(View.VISIBLE);
            cartlayout.setVisibility(View.GONE);
        }
    }
}