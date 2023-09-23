package com.nilscreation.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    TextView amount;
    Button btnPay;
    RadioGroup rgPayment;
    RadioButton gpay, paytm, phonepe;
    EditText txtAddress;
    String paymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        amount = findViewById(R.id.amount);
        btnPay = findViewById(R.id.btnPay);
        rgPayment = findViewById(R.id.rgPayment);
        gpay = findViewById(R.id.gpay);
        paytm = findViewById(R.id.paytm);
        phonepe = findViewById(R.id.phonepe);
        txtAddress = findViewById(R.id.txtAddress);

        rgPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.gpay) {
                    paymentMethod = "Gpay";
                } else if (checkedId == R.id.paytm) {
                    paymentMethod = "Paytm";
                } else {
                    paymentMethod = "PhonePay";
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            amount.setText("" + intent.getFloatExtra("Amount", 0));
        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (paymentMethod != null) {
                    Dialog dialog = new Dialog(PaymentActivity.this);
                    dialog.setContentView(R.layout.order_successful);
                    dialog.setCancelable(false);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    ImageView imageView = dialog.findViewById(R.id.imageView);
                    Animation alpha = AnimationUtils.loadAnimation(PaymentActivity.this, R.anim.alpha);
                    imageView.startAnimation(alpha);

                    Button btn = dialog.findViewById(R.id.btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    dialog.show();
                } else if (txtAddress.getText().toString().isEmpty()) {
                    txtAddress.setError("Enter Valid Address");
                } else {
                    Toast.makeText(PaymentActivity.this, "Please Select Payment Method", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}