package com.example.foodplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button login;
    TextView signup;
    EditText Email , Password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginBtn);
        signup = findViewById(R.id.createNewAccountTxtView);
        Email = findViewById(R.id.emailTxtView);
        Password = findViewById(R.id.passwordTxtView);
        auth = FirebaseAuth.getInstance();

//        login.setOnClickListener(v -> {
//            Intent intent = new Intent(
//                    LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email,pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(LoginActivity.this, StartActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }else{
                        Password.setError("Password cannot be empty");
                    }
                }else if(email.isEmpty()){
                    Email.setError("Email cannot be empty");
                }else{
                    Email.setError("Please enter valid email");
                }
            }
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}