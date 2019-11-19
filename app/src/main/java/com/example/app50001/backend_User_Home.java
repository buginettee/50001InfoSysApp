package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class backend_User_Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend__user__home);

        bottomNavigationView = findViewById(R.id.user_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new UserHomeFragment());
    }



    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container, fragment).commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;

        switch (menuItem.getItemId()){
            case R.id.user_navigation_history:
                fragment = new UserHistoryFragment();
                loadFragment(fragment);
                return true;

            case R.id.user_navigation_home:
                fragment = new UserHomeFragment();
                loadFragment(fragment);
                return true;

            case R.id.user_navigation_guests:
                fragment = new UserAddGuestsFragment();
                loadFragment(fragment);
                return true;

            case R.id.user_navigation_settings:
                fragment = new UserSettingsFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }
}
