package com.lntfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lntfinal.data.UserData;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

//        exampleWrite("abc123", "mamank", "mamank@mail.com");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(MainActivity.this, "There is current user!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "You must login before use this App", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void exampleWrite(String id_bimbel, String name, String email) {
        UserData user = new UserData(id_bimbel, name, email);
        mDatabase.child("example").child(id_bimbel).setValue(user);
    }
}