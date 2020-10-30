package com.nomi.rsixports;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.adapter.CartAdapter;
import com.nomi.rsixports.databinding.ActivityOrderBinding;
import com.nomi.rsixports.model.CartModel;
import com.nomi.rsixports.model.OrderModel;
import com.nomi.rsixports.model.ProfileModel;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;
import com.nomi.rsixports.viewmodel.CartViewModel;
import com.nomi.rsixports.viewmodel.ProfileViewModel;

import java.util.List;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    private CartAdapter adapter;

    private DocumentSnapshot snapshot_profile;
    private List<DocumentSnapshot> snapshots_cart;
    private OrderModel order;
    private Double delivery_charge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        adapter = new CartAdapter(this::delete);

        order = new OrderModel();
        order.setPayment(OrderModel.METHOD.CASH);

        setContentView(binding.getRoot());
        setViewModel();

        binding.buttonReset.setOnClickListener(v -> updateInfo());
        binding.buttonChoose.setOnClickListener(v -> datePicker());

        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        delivery_charge = getIntent().getDoubleExtra("delivery_charge", 5.0);

        String s = String.format(Locale.getDefault(), "Include RM %.2f for delivery charge", delivery_charge);
        binding.textCharge.setText(s);
    }

    private void datePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this);
        dialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);

            order.setDelivered(calendar.getTime());
            updateInfo();
        });
        dialog.show();
    }

    private void delete(DocumentSnapshot snapshot) {
        snapshot.getReference().delete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setEnabled(false);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("process");
        dialog.show();

        if (item.getItemId() == R.id.action_checkout) {
            Shared
                    .getOrderCollectionReference()
                    .add(order)
                    .addOnCompleteListener(task -> {
                        item.setEnabled(true);
                        dialog.dismiss();
                    })
                    .addOnSuccessListener(this::checkoutSuccess)
                    .addOnFailureListener(this::checkoutFailure);
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkoutSuccess(DocumentReference reference) {
        for (DocumentSnapshot snapshot : snapshots_cart) {
            CartModel data = Helper.snapshotToCart(snapshot);

            reference
                    .collection("cart")
                    .add(data);

            snapshot.getReference().delete();
        }

        Intent intent = new Intent(this, SuccessActivity.class);
        startActivity(intent);

        finish();
    }

    private void checkoutFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void setViewModel() {
        ProfileViewModel profile_vm = new ViewModelProvider(this).get(ProfileViewModel.class);
        profile_vm.getData().observe(this, snapshot -> {
            snapshot_profile = snapshot;
            binding.buttonReset.setEnabled(true);
            updateInfo();
        });

        CartViewModel cart_vm = new ViewModelProvider(this).get(CartViewModel.class);
        cart_vm.getData().observe(this, snapshots -> {
            adapter.update(snapshots);
            snapshots_cart = snapshots;

            double total_price = 0.0;

            for (DocumentSnapshot snapshot : snapshots) {
                CartModel data = Helper.snapshotToCart(snapshot);
                total_price += (data.getPrice() * (double) data.getQuantity());
            }

            total_price += delivery_charge; // plus cod

            String s_total = String.format(Locale.getDefault(), "Payment Method: %s\t\tTotal Price: RM %.2f", order.getPayment().toString(), total_price);
            binding.textPayment.setText(s_total);

            String s_size = snapshots.size() + " items in cart";
            binding.textSize.setText(s_size);

            order.setOrder_pay(total_price);
            order.setOrder_charge(delivery_charge);
            order.setOrder_items(snapshots.size());
        });
    }

    private void updateInfo() {
        if (snapshot_profile != null) {
            ProfileModel data = Helper.snapshotToProfile(snapshot_profile);

            order.setAuth_uid(snapshot_profile.getId());

            order.setName(data.getName());
            order.setContact(data.getContact());
            order.setAddress(data.getAddress());

            binding.editName.setText(data.getName());
            binding.editContact.setText(data.getContact());
            binding.editAddress.setText(data.getAddress());
            binding.editDate.setText(order.getStringDelivered());
        }
    }
}