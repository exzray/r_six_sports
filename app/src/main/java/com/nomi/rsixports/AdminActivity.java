package com.nomi.rsixports;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.adapter.OrderAdapter;
import com.nomi.rsixports.databinding.ActivityAdminBinding;
import com.nomi.rsixports.utility.Shared;
import com.nomi.rsixports.viewmodel.OrderViewModel;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setViewModel();
    }

    private void setViewModel() {
        OrderAdapter adapter = new OrderAdapter(this::snapshot);

        OrderViewModel order_vm = new ViewModelProvider(this).get(OrderViewModel.class);
        order_vm.findList();
        order_vm.getList().observe(this, adapter::update);

        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void snapshot(DocumentSnapshot snapshot) {
        Intent intent = new Intent(this, AdminEditActivity.class);
        intent.putExtra(Shared.EXTRA_ORDER_UID, snapshot.getId());

        startActivity(intent);
    }
}