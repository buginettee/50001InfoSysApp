package com.example.app50001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login_Main extends AppCompatActivity implements View.OnClickListener {

    private Button UserLoginButton;
    private Button DeliveryLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_login_main);

        //instantiate everything
        UserLoginButton = findViewById(R.id.UserLoginButton);
        DeliveryLoginButton = findViewById(R.id.DeliveryLoginButton);

        //set their activities
        UserLoginButton.setOnClickListener(Login_Main.this);
        DeliveryLoginButton.setOnClickListener(Login_Main.this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.UserLoginButton:
                Toast.makeText(Login_Main.this, "Entering User Login", Toast.LENGTH_SHORT).show();
                Intent intentUserLogin = new Intent(Login_Main.this, Home_Login.class);
                startActivity(intentUserLogin);
                break;


            case R.id.DeliveryLoginButton:
                Toast.makeText(Login_Main.this, "Entering Delivery Login", Toast.LENGTH_SHORT).show();
                Intent intentDeliveryLogin = new Intent(Login_Main.this, Delivery_Login.class);
                startActivity(intentDeliveryLogin);
                break;
        }
    }
}


//TODO AESTHETICS ADDITION
//Functions have been linked accordingly
