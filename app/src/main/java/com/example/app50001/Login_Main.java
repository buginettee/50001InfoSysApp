package com.example.app50001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login_Main extends AppCompatActivity implements View.OnClickListener {

    private ImageButton HomeChoice;
    private ImageButton DeliveryChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_login_main);

        //instantiate everything
        HomeChoice = findViewById(R.id.HomeChoice);
        DeliveryChoice = findViewById(R.id.DeliveryChoice);

        //set their activities
        HomeChoice.setOnClickListener(Login_Main.this);
        DeliveryChoice.setOnClickListener(Login_Main.this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.HomeChoice:
                Toast.makeText(Login_Main.this, "Entering User Login...", Toast.LENGTH_SHORT).show();
                Intent intentUserLogin = new Intent(Login_Main.this, Home_Login.class);
                startActivity(intentUserLogin);
                break;


            case R.id.DeliveryChoice:
                Toast.makeText(Login_Main.this, "Entering Delivery Login...", Toast.LENGTH_SHORT).show();
                Intent intentDeliveryLogin = new Intent(Login_Main.this, Delivery_Login.class);
                startActivity(intentDeliveryLogin);
                break;
        }
    }
}

//COMPLETED