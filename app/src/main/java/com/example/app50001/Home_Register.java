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
    private EditText RegisterAddress;

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
        RegisterAddress = findViewById(R.id.RegisterAddress);

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
        final String registerAddress = RegisterAddress.getText().toString();
        final HashMap<String, Object> emptyhashmap = new HashMap<>();

        if(!registerUsername.isEmpty() && !registerPassword.isEmpty()){
            mAuth.createUserWithEmailAndPassword(registerUsername, registerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        FirebaseUser newuser = mAuth.getCurrentUser();

                        String newUID = newuser.getUid().toString();

                        //creating the new account
                        mUser.child(newUID).setValue(new Profiles(registerDisplayName,registerUsername,registerAddress,
                                "","","", emptyhashmap,emptyhashmap,emptyhashmap,emptyhashmap,emptyhashmap));


                        Toast.makeText(Home_Register.this, "Registration successful! Please login again.", Toast.LENGTH_SHORT).show();

                        //if created, go to login page again
                        Intent gotoHomeLogin = new Intent(Home_Register.this, Home_Login.class);
                        startActivity(gotoHomeLogin);

                    }
                    else {
                        Toast.makeText(Home_Register.this, "Registration Unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
                        Intent stayonpage = new Intent(Home_Register.this, Home_Register.class);
                        startActivity(stayonpage);
                    }
                }
            });
        }
        else {
            Toast.makeText(Home_Register.this, "Please fill in all the fields", Toast.LENGTH_LONG).show();
        }
    }


}


//DONE
//CAN REGISTER PROPERLY AND CREATING A NEW BRANCH INSIDE THE DATABASE


//WHEN the user registers, there will be no history or access to anything
//initialise this separately inside the home pages
