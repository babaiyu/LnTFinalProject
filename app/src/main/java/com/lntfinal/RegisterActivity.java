package com.lntfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lntfinal.data.UserData;
import com.lntfinal.helpers.Helpers;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText inputBimbel, inputName, inputEmail, inputPassword, inputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("Register");

        inputBimbel = findViewById(R.id.inputBimbel);
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(view -> {
            signUp(inputBimbel.getText().toString(),
                    inputName.getText().toString(),
                    inputEmail.getText().toString(),
                    inputPassword.getText().toString(),
                    inputConfirmPassword.getText().toString());
        });

        btnLogin.setOnClickListener(view -> {
            finish();
        });

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void signUp(String id_bimbel, String name, String email, String password, String confirm_password) {
        Helpers helpers = new Helpers();
        Boolean validIdBimbel = helpers.checkIdBimbel(id_bimbel),
                validName = helpers.checkName(name),
                validEmail = helpers.checkEmail(email),
                validPass = helpers.checkPassword(password),
                validConfirmPass = helpers.checkConfirmPass(password, confirm_password);

        if (!validIdBimbel) {
            helpers.showToast(RegisterActivity.this, "ID Bimbel wajib diisi!");
        }

        if (!validName) {
            helpers.showToast(RegisterActivity.this, "Name harus lebih dari 5 huruf!");
        }

        if (!validEmail) {
            helpers.showToast(RegisterActivity.this, "Format email tidak sesuai!");
        }

        if (!validPass) {
            helpers.showToast(RegisterActivity.this, "Password wajib diisi!");
        }

        if (!validConfirmPass) {
            helpers.showToast(RegisterActivity.this, "Confirm Password tidak sesuai!");
        }

        if (validIdBimbel && validName && validEmail && validPass && validConfirmPass) {
            UserData user = new UserData(id_bimbel, name, email);
            mDatabase.child("user").child(id_bimbel).setValue(user);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUser = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            if (currentUser != null) {
                                currentUser.updateProfile(profileUser).addOnCompleteListener(this, view -> {
                                    if (view.isSuccessful()) {
                                        helpers.showToast(RegisterActivity.this, "Success Create Account");
                                        finish();
                                    } else {
                                        helpers.showToast(RegisterActivity.this, Objects.requireNonNull(view.getException()).getMessage());
                                    }
                                });
                            }
                        } else {
                            String errorMessage = Objects.requireNonNull(task.getException()).getMessage();
                            helpers.showToast(RegisterActivity.this, errorMessage);
                        }
                    });
        }
    }
}