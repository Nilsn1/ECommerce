package com.nilscreation.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    FloatingActionButton cartFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottonNavigationView);
        cartFab = findViewById(R.id.cartFab);
        cartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

        loadFragment(new MainFragment());
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    loadFragment(new MainFragment());
                } else if (id == R.id.settings) {
                    Toast.makeText(HomeActivity.this, "Pending", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.favourite) {
                    Toast.makeText(HomeActivity.this, "Pending", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Pending", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainContainer, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        callExitDialog();
    }

    private void callExitDialog() {
        AlertDialog.Builder exitdialog = new AlertDialog.Builder(this);
        exitdialog.setTitle("Exit");
        exitdialog.setMessage("Do you really want to exit?");
        exitdialog.setIcon(R.drawable.logo);
        exitdialog.setCancelable(false);

        exitdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        exitdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        exitdialog.show();
    }
}