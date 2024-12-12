package com.example.sampleappfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUserName, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupUserName = findViewById(R.id.signup_username);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
//
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SignUpActivity", "Sign Up button clicked");

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUserName.getText().toString();
                String password = signupPassword.getText().toString();

                // Validate input
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store data in Firebase
                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(name).setValue(helperClass);
                Log.d("SignUpActivity", "User data saved to Firebase");

                Toast.makeText(SignUpActivity.this, "You Have Signed up successfully", Toast.LENGTH_SHORT).show();

//                // Navigate to LoginActivity
                Log.d("aaa", "Navigating to LoginActivity");
                Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent1);
            }
        });

        loginRedirectText.setOnClickListener(view -> {
            Log.d("aaaaaa", "loginRedirectText clicked");
            try {
                Intent intent0 = new Intent(SignUpActivity.this, LoginActivity.class);
                Log.d("aaaaa", "Intent created successfully");
                startActivity(intent0);
            } catch (Exception e) {
                Log.e("aaaaa", "Error navigating to LoginActivity", e);
            }
        });


    }
}
