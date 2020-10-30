package com.nomi.rsixports;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.SetOptions;
import com.nomi.rsixports.databinding.ActivityProfileBinding;
import com.nomi.rsixports.model.ProfileModel;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ListenerRegistration registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.buttonUpdate.setOnClickListener(v -> update());

        registration = Shared
                .getProfileDocumentReference()
                .addSnapshotListener((value, error) -> {
                    if (error != null)
                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    if (value != null && value.exists()) {
                        updateUI(value);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registration != null) registration.remove();
    }

    private void update() {
        String s_name = Objects.requireNonNull(binding.editName.getText()).toString().trim();
        String s_contact = Objects.requireNonNull(binding.editContact.getText()).toString().trim();
        String s_address = Objects.requireNonNull(binding.editAddress.getText()).toString().trim();

        if (s_name.isEmpty() || s_contact.isEmpty() || s_address.isEmpty()) {
            Toast.makeText(this, "Please fill all field", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.buttonUpdate.setEnabled(false);

        ProfileModel data = new ProfileModel();
        data.setName(s_name);
        data.setContact(s_contact);
        data.setAddress(s_address);

        Shared
                .getProfileDocumentReference()
                .set(data, SetOptions.merge())
                .addOnCompleteListener(this::updateComplete)
                .addOnSuccessListener(this::updateSuccess)
                .addOnFailureListener(this::updateFailure);
    }

    private void updateComplete(Task<Void> voidTask) {
        binding.buttonUpdate.setEnabled(true);
    }

    private void updateSuccess(Void aVoid) {
        Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
    }

    private void updateFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void updateUI(DocumentSnapshot value) {
        ProfileModel data = Helper.snapshotToProfile(value);

        binding.editName.setText(data.getName());
        binding.editContact.setText(data.getContact());
        binding.editAddress.setText(data.getAddress());
    }
}