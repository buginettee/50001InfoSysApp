package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Home_Register extends AppCompatActivity implements View.OnClickListener {

    private Button BtnRegister;
    private Button BackBtnRegister;
    private EditText RegisterUsername;
    private EditText RegisterPassword;
    private EditText RegisterBox;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__register);


        BtnRegister = findViewById(R.id.BtnRegister);
        BackBtnRegister = findViewById(R.id.BackBtnRegister);
        RegisterUsername = findViewById(R.id.RegisterUsername);
        RegisterPassword = findViewById(R.id.RegisterPassword);
        RegisterBox = findViewById(R.id.RegisterBox);

        mAuth = FirebaseAuth.getInstance();

        BtnRegister.setOnClickListener(Home_Register.this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.BackBtnRegister:
                Intent backtoHomeLogin = new Intent(Home_Register.this, Home_Login.class);
                startActivity(backtoHomeLogin);

            case R.id.BtnRegister:
                registerNewUser();
        }
    }


    private void registerNewUser(){
        String registerUsername = RegisterUsername.getText().toString();
        String registerPassword = RegisterPassword.getText().toString();
        String registerBox = RegisterBox.getText().toString(); //NEED TO FIGURE HOW TO ADD THIS FIELD IN

        if(!registerUsername.isEmpty() && !registerPassword.isEmpty() && !registerBox.isEmpty()){
            mAuth.createUserWithEmailAndPassword(registerUsername, registerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Home_Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();


                        //if created, go to login page again
                        Intent gotoHomeLogin = new Intent(Home_Register.this, Home_Login.class);
                        startActivity(gotoHomeLogin);
                    }
                    else {
                        Toast.makeText(Home_Register.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else {
            Toast.makeText(Home_Register.this, "Please fill in all the fields", Toast.LENGTH_LONG).show();
        }
    }
}
