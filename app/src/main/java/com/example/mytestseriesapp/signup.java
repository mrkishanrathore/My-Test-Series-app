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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class signup extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText editName, editEmail, editPassword, editConfirmPass;
    Button signIn ;
    FirebaseAuth mAuth;
    ProgressBar bar;

//for google signIn
    com.google.android.gms.common.SignInButton GoogleSignInBtn;
    @Override public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), DashBoard.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance(Objects.requireNonNull(FirebaseApp.initializeApp(this))); // Initialize Firebase

//        for google sign in
        GoogleSignInBtn = findViewById(R.id.btnGoogleSignIn);
        GoogleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, GoogleSignIn1.class);
                startActivity(intent);
            }
        });

        editName = findViewById(R.id.name);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editConfirmPass = findViewById(R.id.confirmPass);
        signIn = findViewById(R.id.signIn);
        bar = findViewById(R.id.progressBar);

        signIn.setOnClickListener(view -> {
            String name, email, password, confirmPass;
            password = String.valueOf(editPassword.getText());
            confirmPass = String.valueOf(editConfirmPass.getText());
            name = Objects.requireNonNull(editName.getText()).toString();
            email = String.valueOf(editEmail.getText());

            if(name.isEmpty()){
                Toast.makeText(signup.this,"Enter name!",Toast.LENGTH_SHORT).show();
                return;
            }

            if(email.isEmpty()){
                Toast.makeText(signup.this,"Enter email!",Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.length()<8){
                Toast.makeText(signup.this,"Enter Password of length 8 or more!",Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.equals(confirmPass)){
                Toast.makeText(signup.this,name + email,Toast.LENGTH_SHORT).show();
                // Call the signIn method
                bar.setVisibility(View.VISIBLE);
                signInWithEmailAndPassword(email, password,name);
            }
            else{
                    Toast.makeText(signup.this,"Please, Enter password and ConfirmPassword correctly",Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void Login(View v){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void signInWithEmailAndPassword(String email, String password,String name) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this,"SignIn SuccessFully!",Toast.LENGTH_SHORT).show();
//
//                  FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build();
                    assert user != null;
                    user.updateProfile(profileUpdates);

                    startActivity(new Intent(signup.this, DashBoard.class));

                }else {
                    Toast.makeText(signup.this,"SignIn Unsuccessful, Please try again!",Toast.LENGTH_SHORT).show();
                }
                bar.setVisibility(View.GONE);
            }
        });
    }

}