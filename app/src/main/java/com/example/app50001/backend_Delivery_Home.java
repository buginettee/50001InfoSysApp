package com.example.app50001;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class backend_Delivery_Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_delivery_home);


        bottomNavigationView = findViewById(R.id.delivery_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.delivery_navigation_home);
    }

    private boolean loadFragment(Fragment fragment) {
        if( fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.delivery_fragment_container, fragment).commit();

            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment;

        switch(menuItem.getItemId()){
            case R.id.delivery_navigation_history: //REFERENCE FROM THE MENU ID
                fragment = new DeliveryHistoryFragment();
                loadFragment(fragment);
                return true;

            case R.id.delivery_navigation_home:
                fragment = new DeliveryHomeFragment();
                loadFragment(fragment);
                return true;

            case R.id.delivery_navigation_settings:
                fragment = new DeliverySettingsFragment();
                loadFragment(fragment);
                return true;
        }
        return true;
    }
}

//COMPLETED