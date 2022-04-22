package com.lntfinal;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText inputBimbel, inputName, inputEmail, inputPassword, inputConfirmPassword;
    private Button btnRegister;
    private TextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("Register");

        inputBimbel = findViewById(R.id.inputBimbel);
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            finish();
        });

        mAuth = FirebaseAuth.getInstance();
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
                        Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }
}