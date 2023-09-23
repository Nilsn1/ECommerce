package com.nilscreation.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvPrice, tvDescription;
    ImageView productImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        productImg = findViewById(R.id.productImg);

        Intent intent = getIntent();
        if (intent != null) {
            tvTitle.setText(intent.getStringExtra("Title"));
            tvDescription.setText(intent.getStringExtra("Description"));
            tvPrice.setText("" + intent.getFloatExtra("Price", 0));

            Glide.with(this).load(intent.getStringExtra("Url")).into(productImg);
        }
    }
}