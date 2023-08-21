package com.nilscreation.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nilscreation.ecommerce.model.Address;
import com.nilscreation.ecommerce.model.UserModel;
import com.nilscreation.ecommerce.service.ApiService;
import com.nilscreation.ecommerce.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private ApiService apiService;
    EditText signupUsername, signupEmail, signupCity, signupZipcode, signupPassword;
    Button btnSignup;

    String username, userEmail, userCity, userZipcode, userPassword;

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

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = signupUsername.getText().toString();
                userEmail = signupEmail.getText().toString();
                userCity = signupCity.getText().toString();
                userZipcode = signupZipcode.getText().toString();
                userPassword = signupPassword.getText().toString();

                signup();
            }
        });

        apiService = RetrofitClient.getClient("https://fakestoreapi.com/").create(ApiService.class);


    }

    private void signup() {
        // Create a User object with the registration data
        Address address = new Address(userCity, userZipcode);
        UserModel user = new UserModel(userEmail, username, userPassword, address);

        // Make the registration request
        Call<Void> call = apiService.registerUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "RError", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
            }
        });
    }
}