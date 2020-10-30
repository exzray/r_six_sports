package com.nomi.rsixports;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nomi.rsixports.databinding.ActivityRegisterBinding;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.buttonSignup.setOnClickListener(v -> signup());
    }

    private void signup() {
        String email = Objects.requireNonNull(binding.editEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.editPassword.getText()).toString().trim();
        String retype = Objects.requireNonNull(binding.editRetype.getText()).toString().trim();

        if (email.isEmpty() || password.isEmpty() || retype.isEmpty()) {
            Toast.makeText(this, "All field must not blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(retype)) {
            Toast.makeText(this, "Password must be same", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.buttonSignup.setEnabled(false);

        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this::signupComplete)
                .addOnSuccessListener(this::signupSuccess)
                .addOnFailureListener(this::signupFailure);
    }

    private void signupComplete(Task<AuthResult> authResultTask) {
        binding.buttonSignup.setEnabled(true);
    }

    private void signupSuccess(AuthResult result) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    private void signupFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}