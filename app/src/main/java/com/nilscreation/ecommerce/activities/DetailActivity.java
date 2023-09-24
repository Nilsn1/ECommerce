package com.nilscreation.ecommerce.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nilscreation.ecommerce.R;
import com.nilscreation.ecommerce.service.MyDBHelper;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvPrice, tvDescription, tvQty;
    ImageView plusBtn, minusBtn, productImg;
    Button cartBtn;

    //product data
    String title, image;
    float mprice, itemFinalPrice, mfinalPrice;
    int mid;
    int qtyNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvPrice = findViewById(R.id.tvPrice);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        tvDescription = findViewById(R.id.tvDescription);
        tvQty = findViewById(R.id.tvQty);
        productImg = findViewById(R.id.productImg);
        cartBtn = findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper myDBHelper = new MyDBHelper(DetailActivity.this);
                myDBHelper.deleteandAdd(String.valueOf(mid), title, mprice, qtyNumber, itemFinalPrice, mfinalPrice, image);
                Toast.makeText(DetailActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {

            mid = intent.getIntExtra("Id", 0);
            title = intent.getStringExtra("Title");
            mprice = intent.getFloatExtra("Price", 0);
            image = intent.getStringExtra("Url");

            itemFinalPrice = mprice * qtyNumber;


            tvTitle.setText(intent.getStringExtra("Title"));
            tvDescription.setText(intent.getStringExtra("Description"));
            tvPrice.setText("" + intent.getFloatExtra("Price", 0));

            Glide.with(this).load(intent.getStringExtra("Url")).into(productImg);
        }

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyNumber = qtyNumber + 1;
                tvQty.setText(String.valueOf(qtyNumber));
                itemFinalPrice = mprice * qtyNumber;

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedPrice = decimalFormat.format(itemFinalPrice);
                tvPrice.setText("" + formattedPrice);
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qtyNumber > 1) {
                    qtyNumber = qtyNumber - 1;
                }
                tvQty.setText(String.valueOf(qtyNumber));
                itemFinalPrice = mprice * qtyNumber;

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedPrice = decimalFormat.format(itemFinalPrice);
                tvPrice.setText("" + formattedPrice);
            }
        });
    }
}