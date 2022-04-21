package com.lntfinal;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String title = "Login";
            actionBar.setTitle(title);
        }

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) Log.d("USER", "User already exist");
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("EmailAndPassword", "Success Login");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("EmailAndPassword", user.getEmail());
                    } else {
                        String errorMessage = task.getException().getMessage();
                        Log.w("EmailAndPassword", errorMessage);
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void signUp(String id_bimbel, String name, String email, String password, String confirm_password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("CREATE_USER", "Success create User");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("USER", user.getEmail());
                    } else {
                        String errorMessage = task.getException().getMessage();
                        Log.w("EmailAndPassword", errorMessage);
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
    }
}