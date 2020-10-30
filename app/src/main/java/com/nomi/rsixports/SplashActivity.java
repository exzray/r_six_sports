package com.nomi.rsixports;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nomi.rsixports.databinding.ActivitySplashBinding;
import com.nomi.rsixports.model.ProfileModel;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setImageLogo();
        setButton();
    }

    private void setButton() {
        binding.buttonLogin.setOnClickListener(v -> startActivityLogin());
        binding.buttonRegister.setOnClickListener(v -> startActivityRegister());
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);
    }

    private void setImageLogo() {
        Glide.with(this).load(R.drawable.logo).circleCrop().into(binding.imageLogo);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("load user data");
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {

                Shared
                        .getProfileDocumentReference()
                        .get()
                        .addOnSuccessListener(snapshot -> {
                            if (snapshot == null) return;
                            ProfileModel data = Helper.snapshotToProfile(snapshot);

                            if (data.getSuperuser()) startActivityAdmin();
                            else startActivityMain();
                        })
                        .addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());

                dialog.dismiss();

            }, 2000);
        }

    }

    private void startActivityLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void startActivityRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void startActivityMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    private void startActivityAdmin() {
        Intent intent = new Intent(this, AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}