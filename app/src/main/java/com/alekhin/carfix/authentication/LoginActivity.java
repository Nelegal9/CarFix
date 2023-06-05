package com.alekhin.carfix.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alekhin.carfix.MainActivity;
import com.alekhin.carfix.R;
import com.alekhin.carfix.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.signInButton.setOnClickListener(v -> {
            String email = String.valueOf(binding.emailTextField.getText());
            String password = String.valueOf(binding.passwordTextField.getText());

            if (inputCheck(email, password)) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_LONG).show();
                });
            } else Toast.makeText(getApplicationContext(), R.string.add_failed, Toast.LENGTH_LONG).show();
        });

        binding.signUpTextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private Boolean inputCheck(String email, String password) {
        return !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password));
    }
}