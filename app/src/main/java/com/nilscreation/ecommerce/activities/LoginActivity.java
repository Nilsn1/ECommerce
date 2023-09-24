package com.nilscreation.ecommerce.activities;

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
import android.widget.Toast;

import com.nilscreation.ecommerce.R;
import com.nilscreation.ecommerce.model.UserLogin;
import com.nilscreation.ecommerce.service.ApiService;
import com.nilscreation.ecommerce.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button btnLogin;
    ImageView ImgshowPassword;
    TextView txtSignup;
    boolean showpassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.loginUsername);
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                if (username.isEmpty()) {
                    loginUsername.setError("Username Required");
                } else if (password.isEmpty()) {
                    loginPassword.setError("Password Required");
                } else {
                    loginUser(username, password);
                }
            }
        });
    }

    private void loginUser(String username, String password) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        UserLogin loginRequest = new UserLogin(username, password);
        Call<Void> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Error:" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}