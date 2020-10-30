package com.nomi.rsixports;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.ListenerRegistration;
import com.nomi.rsixports.adapter.CartAdapter;
import com.nomi.rsixports.databinding.ActivityAdminEditBinding;
import com.nomi.rsixports.model.CartModel;
import com.nomi.rsixports.model.OrderModel;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;
import com.nomi.rsixports.viewmodel.OrderViewModel;

import java.util.Locale;

public class AdminEditActivity extends AppCompatActivity {

    private ActivityAdminEditBinding binding;
    private ListenerRegistration registration;

    private OrderModel order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminEditBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (registration != null) registration.remove();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_edit_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            CharSequence[] choices = {"PENDING", "CANCEL", "APPROVE", "COMPLETE"};

            int index = 0;

            switch (order.getStatus()) {
                case PENDING:
                    index = 0;
                    break;
                case CANCEL:
                    index = 1;
                    break;
                case APPROVE:
                    index = 2;
                    break;
                case COMPLETE:
                    index = 3;
                    break;
            }

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("change order status");
            builder.setSingleChoiceItems(choices, index, (dialog, which) -> {
                binding.textStatus.setText(choices[which]);
                order.setStatus(OrderModel.STATUS.valueOf((String) choices[which]));
            });
            builder.setPositiveButton("OK", null);
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewModel() {
        String order_id = getIntent().getStringExtra(Shared.EXTRA_ORDER_UID);

        OrderViewModel order_vm = new ViewModelProvider(this).get(OrderViewModel.class);
        order_vm.findData(order_id);
        order_vm.getData().observe(this, snapshot -> {
            if (snapshot == null) return;

            OrderModel data = Helper.snapshotToOrder(snapshot);

            binding.textName.setText(data.getName());
            binding.textContact.setText(data.getContact());
            binding.textAddress.setText(data.getAddress());

            binding.textItems.setText(data.getStringQuantity());
            binding.textCharge.setText(data.getStringCharge());
            binding.textPayment.setText(data.getStringPay());
            binding.textCreated.setText(data.getStringCreated());
            binding.textDeliver.setText(data.getStringDelivered());
            binding.textMethod.setText(data.getStringMethod());
            binding.textStatus.setText(data.getStringStatus());

            order = data;

            binding.buttonUpdate.setEnabled(true);
            binding.buttonUpdate.setOnClickListener(v -> {
                binding.buttonUpdate.setEnabled(false);

                snapshot
                        .getReference()
                        .set(order)
                        .addOnCompleteListener(task -> binding.buttonUpdate.setEnabled(true))
                        .addOnSuccessListener(aVoid -> Toast.makeText(this, "Success update order", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
            });

            getItems(snapshot);
        });
    }

    private void getItems(DocumentSnapshot snapshot) {
        CartAdapter adapter = new CartAdapter(this::snapshot);

        registration = snapshot
                .getReference()
                .collection("cart")
                .addSnapshotListener((value, error) -> {
                    if (error != null)
                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    adapter.update(value.getDocuments());

                    double total = 0.0 + order.getOrder_charge();
                    int size = value.size();

                    for (DocumentSnapshot snap : value.getDocuments()) {
                        CartModel data = Helper.snapshotToCart(snap);

                        total += data.getPrice() * (double) data.getQuantity();
                    }

                    String s_items = String.format(Locale.getDefault(), "RM %.2f", total);
                    binding.textPayment.setText(s_items);

                    binding.textItems.setText(String.valueOf(size));
                });

        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void snapshot(DocumentSnapshot snapshot) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setMessage("are sure to remove because item is out of stock?");
        builder.setPositiveButton("Sure", (dialog, which) -> snapshot.getReference().delete());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}