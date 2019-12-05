package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Home_Register extends AppCompatActivity implements View.OnClickListener {

    private Button BtnRegister;
    private Button BackBtnRegister;
    private EditText RegisterUsername;
    private EditText RegisterPassword;
    private EditText RegisterDisplayName;

    private FirebaseAuth mAuth;
    private DatabaseReference mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__register);


        BtnRegister = findViewById(R.id.BtnRegister);
        BackBtnRegister = findViewById(R.id.BackBtnRegister);
        RegisterUsername = findViewById(R.id.RegisterUsername);
        RegisterPassword = findViewById(R.id.RegisterPassword);
        RegisterDisplayName = findViewById(R.id.RegisterDisplayName);

        mAuth = FirebaseAuth.getInstance();

        BtnRegister.setOnClickListener(Home_Register.this);
        BackBtnRegister.setOnClickListener(Home_Register.this);

        mUser = FirebaseDatabase.getInstance().getReference().child("Profiles");


    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.BtnRegister:
                registerNewUser();

            case R.id.BackBtnRegister:
                Intent backtoHomeLogin = new Intent(Home_Register.this, Login_Main.class);
                startActivity(backtoHomeLogin);

        }
    }


    private void registerNewUser(){
        final String registerUsername = RegisterUsername.getText().toString();
        String registerPassword = RegisterPassword.getText().toString();
        final String registerDisplayName = RegisterDisplayName.getText().toString();
        final HashMap<String, Object> emptyhashmap = new HashMap<>();

        if(!registerUsername.isEmpty() && !registerPassword.isEmpty()){
            mAuth.createUserWithEmailAndPassword(registerUsername, registerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        FirebaseUser newuser = mAuth.getCurrentUser();

                        String newUID = newuser.getUid().toString();

                        //creating the new account
                        Profiles newprofile = new Profiles();
                        newprofile.setEmail(registerUsername);
                        newprofile.setDisplayName(registerDisplayName);
                        newprofile.setAdminOf(emptyhashmap);
                        newprofile.setGuestOf(emptyhashmap);
                        newprofile.setDeliveryOf(emptyhashmap);
                        newprofile.setUserHistoryOfProfile(emptyhashmap);
                        newprofile.setDeliveryHistoryOfProfile(emptyhashmap);

                        mUser.child(newUID).setValue(newprofile);

                        Toast.makeText(Home_Register.this, "Registration successful! Please login again.", Toast.LENGTH_SHORT).show();

                        //if created, go to login page again
                        Intent gotoHomeLogin = new Intent(Home_Register.this, Home_Login.class);
                        startActivity(gotoHomeLogin);

                    }
                    else {
                        Toast.makeText(Home_Register.this, "Unsuccessful. Please try again by using a valid email and more than 8 character long password.", Toast.LENGTH_LONG).show();
                        Intent stayonpage = new Intent(Home_Register.this, Home_Register.class);
                        startActivity(stayonpage);
                    }
                }
            });
        }
        else {
            Toast.makeText(Home_Register.this, "Please fill in all the fields.", Toast.LENGTH_LONG).show();
        }
    }


}

//COMPLETED
