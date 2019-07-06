package com.example.nutritions;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
            finishActivity(1);
        }
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Register button clicked!");
                loadingProgressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(usernameEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                        } else {showLoginFailed("Failed to log in");
                        loadingProgressBar.setVisibility(View.INVISIBLE);
                            System.out.println("Username: "+usernameEditText.getText().toString());
                            System.out.println("Password: "+passwordEditText.getText().toString());
                        }
                    }
                });
            }
        });




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login button clicked!");
                loadingProgressBar.setVisibility(View.VISIBLE);
                String usernameString = usernameEditText.getText().toString();
                String passwordString = passwordEditText.getText().toString();
                if (usernameString.equals("")) {
                    showLoginFailed("Username can't be empty");
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                if (passwordString.equals("")) {
                    showLoginFailed("Password can't be empty");
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                Task<AuthResult> signIn = mAuth.signInWithEmailAndPassword(usernameString, passwordString);
                signIn.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            System.out.println("Success login");
                            finish();
                        } else {
                            System.out.println("Username: "+usernameEditText.getText().toString());
                            System.out.println("Password: "+passwordEditText.getText().toString());
                            showLoginFailed("Login failed");
                            System.out.println(task.getException());
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });
    }


    private void showLoginFailed(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
