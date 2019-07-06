package com.example.nutritions;

import androidx.annotation.NonNull;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseAuthWebException;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
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

                Task<AuthResult> registerTask = mAuth.createUserWithEmailAndPassword(usernameString, passwordString);
                registerTask.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        finish();
                    }
                });
                registerTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showLoginFailed(e.getLocalizedMessage());
                        loadingProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                Task<AuthResult> signInTask = mAuth.signInWithEmailAndPassword(usernameString, passwordString);
                signInTask.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        finish();
                    }
                });
                signInTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showLoginFailed(e.getLocalizedMessage());
                        loadingProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }


    private void showLoginFailed(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
