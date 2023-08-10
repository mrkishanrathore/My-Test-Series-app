package com.example.mytestseriesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText  editEmail, editPassword;
    Button logIn ;
    ProgressBar bar;
    FirebaseAuth mAuth;

@Override public void onStart(){
    super.onStart();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    try {
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), DashBoard.class);
            startActivity(intent);
            finish();
        }
    }catch (Exception e){
        Toast.makeText(Login.this,"Please login!",Toast.LENGTH_SHORT).show();
    }

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(Objects.requireNonNull(FirebaseApp.initializeApp(this))); // Initialize Firebase

        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        logIn = findViewById(R.id.login);
        bar = findViewById(R.id.progressBar);

        logIn.setOnClickListener(view -> {
            String email, password;
            password = String.valueOf(editPassword.getText());
            email = String.valueOf(editEmail.getText());

            if(email.isEmpty()){
                Toast.makeText(Login.this,"Enter email!",Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.length()<8){
                Toast.makeText(Login.this,"Enter a valid Password of length 8 or more!",Toast.LENGTH_SHORT).show();
                return;
            }

            bar.setVisibility(View.VISIBLE);
                logInWithEmailAndPassword(email, password);

        });

    }

    public void SignIn(View v){
        finish();
    }

    private void logInWithEmailAndPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"You are logged In!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(intent);
                    bar.setVisibility(View.GONE);
                    finish();
                }
                else {
                    Toast.makeText(Login.this,"You are not Signed In!",Toast.LENGTH_SHORT).show();
                }
                bar.setVisibility(View.GONE);
            }
        });
    }
}