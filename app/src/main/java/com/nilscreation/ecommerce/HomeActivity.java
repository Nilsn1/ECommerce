package com.nilscreation.ecommerce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nilscreation.ecommerce.fragment.MainFragment;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottonNavigationView);

        loadFragment(new MainFragment());

//        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//
//                if (id == R.id.home) {
//                    loadFragment(new MainFragment());
//                } else if (id == R.id.settings) {
//                    loadFragment(new SettingsFragment());
//                } else if (id == R.id.favourite) {
//                    loadFragment(new FavouriteFragment());
//                } else {
//                    loadFragment(new CategoryFragment());
//                }
//                return true;
//            }
//        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainContainer, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragInstance = fm.findFragmentById(R.id.mainContainer);
        callExitDialog();

//        if (fragInstance instanceof CategoryFragment) {
//            loadFragment(new MainFragment());
//            bottomNavigation.setSelectedItemId(R.id.home);
//        } else if (fragInstance instanceof FavouriteFragment) {
//            loadFragment(new MainFragment());
//            bottomNavigation.setSelectedItemId(R.id.home);
//        } else if (fragInstance instanceof SettingsFragment) {
//            loadFragment(new MainFragment());
//            bottomNavigation.setSelectedItemId(R.id.home);
//        } else {
//            callExitDialog();
//        }
    }

    private void callExitDialog() {
        AlertDialog.Builder exitdialog = new AlertDialog.Builder(this);
        exitdialog.setTitle("Exit").setMessage("Do you really want to exit?");

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