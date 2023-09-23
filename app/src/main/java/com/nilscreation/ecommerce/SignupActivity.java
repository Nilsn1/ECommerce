package com.nilscreation.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nilscreation.ecommerce.model.Address;
import com.nilscreation.ecommerce.model.UserModel;
import com.nilscreation.ecommerce.service.ApiService;
import com.nilscreation.ecommerce.service.RetrofitClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private ApiService apiService;
    EditText signupUsername, signupEmail, signupCity, signupZipcode, signupPassword;
    Button btnSignup;
    String username, userEmail, userCity, userZipcode, userPassword;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupUsername = findViewById(R.id.signupUsername);
        signupEmail = findViewById(R.id.signupEmail);
        signupCity = findViewById(R.id.signupCity);
        signupZipcode = findViewById(R.id.signupZipcode);
        signupPassword = findViewById(R.id.signupPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tvLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = signupUsername.getText().toString();
                userEmail = signupEmail.getText().toString();
                userCity = signupCity.getText().toString();
                userZipcode = signupZipcode.getText().toString();
                userPassword = signupPassword.getText().toString();

                if (username.isEmpty()) {
                    signupUsername.setError("Username Required");
                } else if (userEmail.isEmpty()) {
                    signupEmail.setError("Username Required");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    signupEmail.setError("Invalid Email Address");
                } else if (userCity.isEmpty()) {
                    signupCity.setError("Username Required");
                } else if (userZipcode.isEmpty()) {
                    signupZipcode.setError("Username Required");
                } else if (userPassword.isEmpty()) {
                    signupPassword.setError("Username Required");
                } else {
                    signup();
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        apiService = RetrofitClient.getClient().create(ApiService.class);

    }

    private void signup() {
        Address address = new Address(userCity, userZipcode);
        UserModel user = new UserModel(userEmail, username, userPassword, address);

        Call<Void> call = apiService.registerUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}