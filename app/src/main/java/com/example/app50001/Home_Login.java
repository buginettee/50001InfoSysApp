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

public class Home_Login extends AppCompatActivity {

    private Button BtnHomeLogin;
    private Button BtnHomeRegister;
    private Button BackBtnHome;
    private EditText HomeUsername;
    private EditText HomePassword;

    private FirebaseAuth UserFBAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__login);

        BtnHomeLogin = findViewById(R.id.BtnHomeLogin);
        BtnHomeRegister = findViewById(R.id.BtnHomeRegister);
        BackBtnHome = findViewById(R.id.BackBtnHome);
        HomeUsername = findViewById(R.id.HomeUsername);
        HomePassword = findViewById(R.id.HomePassword);



        BtnHomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginHome();
            }
        });

        BtnHomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerUser = new Intent(Home_Login.this, Home_Register.class);
                Toast.makeText(Home_Login.this, "Entering Register", Toast.LENGTH_SHORT).show();
                startActivity(registerUser);
            }
        });

        BackBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backtoLoginMainUser = new Intent(Home_Login.this, Login_Main.class);
                startActivity(backtoLoginMainUser);
            }
        });




        UserFBAuth = FirebaseAuth.getInstance();
    }

    private void loginHome() {

        String HUSername = HomeUsername.getText().toString();
        String HPassword = HomePassword.getText().toString();

        if(!HUSername.isEmpty() && !HPassword.isEmpty()){
            UserFBAuth.signInWithEmailAndPassword(HUSername, HPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Home_Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        Intent HomeLoginIntent = new Intent(Home_Login.this, backend_User_Home.class);
                        startActivity(HomeLoginIntent);
                    }
                    else {
                        Toast.makeText(Home_Login.this, "Login Unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(Home_Login.this, "Please fill up all the fields", Toast.LENGTH_LONG).show();
        }
    }
}

//DONE