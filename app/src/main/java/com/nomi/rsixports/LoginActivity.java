package com.nomi.rsixports;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nomi.rsixports.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.buttonSignin.setOnClickListener(v -> signin());
    }

    private void signin() {
        String email = Objects.requireNonNull(binding.editEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.editPassword.getText()).toString().trim();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "All field must not blank", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.buttonSignin.setEnabled(false);

        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this::signinComplete)
                .addOnSuccessListener(this::signinSuccess)
                .addOnFailureListener(this::signinFailure);
    }

    private void signinComplete(Task<AuthResult> authResultTask) {
        binding.buttonSignin.setEnabled(true);
    }

    private void signinSuccess(AuthResult result) {
        onBackPressed();
    }

    private void signinFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}