package com.nomi.rsixports;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nomi.rsixports.adapter.HistoryAdapter;
import com.nomi.rsixports.databinding.ActivityHistoryBinding;
import com.nomi.rsixports.utility.Shared;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding binding;
    private HistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        adapter = new HistoryAdapter(this::snapshot);

        setContentView(binding.getRoot());

        setRecycler();
    }


    private void snapshot(DocumentSnapshot snapshot) {
        snapshot.getReference().delete().addOnSuccessListener(aVoid -> Toast.makeText(this, "Success delete history", Toast.LENGTH_SHORT).show());
    }

    private void setRecycler() {
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Shared
                .getFirestore()
                .collection("order")
                .whereEqualTo("auth_uid", Shared.getUser().getUid())
                .get()
                .addOnSuccessListener(this::orderSuccess)
                .addOnFailureListener(this::orderFailure);
    }

    private void orderSuccess(QuerySnapshot query) {
        if (query == null) return;
        adapter.update(query.getDocuments());
    }

    private void orderFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}