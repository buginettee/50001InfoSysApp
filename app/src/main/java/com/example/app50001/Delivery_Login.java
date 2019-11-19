package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Delivery_Login extends AppCompatActivity implements View.OnClickListener {
    private Button BtnDeliveryLogin;
    private Button BackBtnDelivery;
    private EditText DeliveryUsername;
    private EditText DeliveryPassword;

    private FirebaseAuth UserFBAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);

        BtnDeliveryLogin = findViewById(R.id.BtnDeliveryLogin);
        BackBtnDelivery = findViewById(R.id.BackBtnDelivery);
        DeliveryUsername = findViewById(R.id.DeliveryUsername);
        DeliveryPassword = findViewById(R.id.DeliveryPassword);


        BtnDeliveryLogin.setOnClickListener(Delivery_Login.this);
        BackBtnDelivery.setOnClickListener(Delivery_Login.this);

        UserFBAuth = FirebaseAuth.getInstance();


    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.BtnDeliveryLogin:
                loginDelivery();


            case R.id.BackBtnDelivery:
                Intent backtoLoginMain = new Intent(Delivery_Login.this, Login_Main.class);
                startActivity(backtoLoginMain);
        }
    }

    private void loginDelivery() {

        String DUSername = DeliveryUsername.getText().toString();
        String DPassword = DeliveryPassword.getText().toString();

        if(!DUSername.isEmpty() && !DPassword.isEmpty()){
            UserFBAuth.signInWithEmailAndPassword(DUSername, DPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Delivery_Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Log.d("Delivery Login", "Delivery Login Successful");




                        Intent DeliveryLoginIntent = new Intent(Delivery_Login.this, backend_Delivery_Home.class);
                        startActivity(DeliveryLoginIntent);

                    }
                    else {
                        Toast.makeText(Delivery_Login.this, "Login Unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.d("Delivery Login", "Delivery Login Unsuccessful");

                        //HOW TO STAY ON THE PAGE IF THE LOGIN DETAILS ARE WRONG??

                    }
                }
            });
        } else {
            Toast.makeText(Delivery_Login.this, "Please fill up all the fields", Toast.LENGTH_LONG).show();

        }
    }
}

//TODO AESTHETICS OF THE SCREEN
//TODO SEGMENT THE DELIVERY USERNAMES FROM THE HOME USERNAMES
//TODO TIE UNIQUE DELIVERY PROFILE TO THEIR PROFILE INFORMATION
//TODO AWKWARD TRANSITION PAGE DURING LOGIN (it will show the main login page before loading into the delivery home page)

//Delivery will not have a registration page as they should have been pre-registered into the system already

//Can login already, need to tidy the transitions up