package com.nilscreation.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button btnLogin;
    ImageView ImgshowPassword;
    TextView txtSignup;
    boolean showpassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        ImgshowPassword = findViewById(R.id.showPassword);
        txtSignup = findViewById(R.id.txtSignup);

        ImgshowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showpassword) {
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ImgshowPassword.setImageResource(R.drawable.ic_show);
                } else {
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ImgshowPassword.setImageResource(R.drawable.ic_hide);
                }
                showpassword = !showpassword;
                loginPassword.setSelection(loginPassword.getText().toString().length());
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}