package com.nomi.rsixports;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.nomi.rsixports.adapter.CategoryPagerAdapter;
import com.nomi.rsixports.databinding.ActivityMainBinding;
import com.nomi.rsixports.model.ConfigModel;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;
import com.nomi.rsixports.viewmodel.CartViewModel;
import com.nomi.rsixports.viewmodel.CategoryViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CategoryPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        adapter = new CategoryPagerAdapter(getSupportFragmentManager());

        setContentView(binding.getRoot());

        setNavigation();
        setViewModel();
        setFab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNavigation() {
        binding.pager.setAdapter(adapter);
        binding.tab.setupWithViewPager(binding.pager);
    }

    private void setViewModel() {
        CategoryViewModel category_vm = new ViewModelProvider(this).get(CategoryViewModel.class);
        category_vm.getData().observe(this, snapshots -> adapter.update(snapshots));

        CartViewModel cart_vm = new ViewModelProvider(this).get(CartViewModel.class);
        cart_vm.getData().observe(this, snapshots -> {
            if (snapshots.isEmpty()) binding.fab.hide();
            else binding.fab.show();
        });
    }

    private void setFab() {
        binding.fab.setOnClickListener(v -> {

            Shared
                    .getFirestore()
                    .collection("store")
                    .document("config")
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot == null) {
                            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ConfigModel data = Helper.snapshotToConfig(snapshot);

                        Intent intent = new Intent(this, OrderActivity.class);
                        intent.putExtra("delivery_charge", data.getDelivery_charge());
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}