package com.nomi.rsixports;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.adapter.ImageAdapter;
import com.nomi.rsixports.databinding.ActivityDetailBinding;
import com.nomi.rsixports.model.CartModel;
import com.nomi.rsixports.model.ProductModel;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;
import com.nomi.rsixports.viewmodel.ProductViewModel;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    private final CartModel cart = new CartModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        String product_id = getIntent().getStringExtra(Shared.EXTRA_PRODUCT_UID);

        ProductViewModel vm = new ViewModelProvider(this).get(ProductViewModel.class);
        vm.setProduct(product_id);
        vm.getProduct().observe(this, this::updateUI);
    }

    private void updateUI(DocumentSnapshot snapshot) {
        ProductModel data = Helper.snapshotToProduct(snapshot);

        cart.setName(data.getName());
        cart.setBrand(data.getBrand());
        cart.setCategory_uid(data.getCategory_uid());
        cart.setDescription(data.getDescription());
        cart.setPrice(data.getPrice());
        cart.setQuantity(0);

        ImageAdapter adapter = new ImageAdapter();
        adapter.update(data.getImages());

        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.recycler);

        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        binding.textName.setText(data.getName());
        binding.textDescription.setText(data.getDescription());

        String price = String.format(Locale.getDefault(), "Price: %s", data.getPriceString());
        binding.textPrice.setText(price);

        if (!data.getChoices().isEmpty()) {
            binding.container1.setVisibility(View.VISIBLE);
            binding.buttonChoose.setVisibility(View.VISIBLE);

            cart.setChoose(data.getChoices().get(0));

            binding.textColor.setText(cart.getChoose());
            binding.buttonChoose.setOnClickListener(v -> {
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
                adapter1.addAll(data.getChoices());

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Color variation");
                builder.setSingleChoiceItems(adapter1, 0, (dialog, which) -> {
                    cart.setChoose(adapter1.getItem(which));
                    stateChange();
                    dialog.dismiss();
                });

                builder.create().show();
            });

        } else {
            binding.container1.setVisibility(View.GONE);
            binding.buttonChoose.setVisibility(View.GONE);
        }

        binding.buttonUp.setOnClickListener(v -> {
            int counter = cart.getQuantity() + 1;
            cart.setQuantity(counter);
            stateChange();
        });

        binding.buttonDown.setOnClickListener(v -> {
            int counter = cart.getQuantity() - 1;
            if (counter < 0) counter = 0;
            cart.setQuantity(counter);
            stateChange();
        });

        binding.buttonAdd.setOnClickListener(v -> {
            if (cart.getQuantity() == 0) {
                Toast.makeText(this, "Quantity must not zero", Toast.LENGTH_SHORT).show();
                return;
            }

            binding.buttonAdd.setEnabled(false);

            Shared
                    .getCartCollectionReference()
                    .add(cart)
                    .addOnCompleteListener(this::cartComplete)
                    .addOnSuccessListener(this::cartSuccess)
                    .addOnFailureListener(this::cartFailure);
        });

        stateChange();
    }

    private void cartComplete(Task<DocumentReference> documentReferenceTask) {
        binding.buttonAdd.setEnabled(true);
    }

    private void cartSuccess(DocumentReference documentReference) {
        Toast.makeText(this, "Success add to cart", Toast.LENGTH_SHORT).show();

        new MaterialAlertDialogBuilder(this)
                .setMessage("Do you want to open cart now?")
                .setPositiveButton("YES", (dialog, which) -> {
                    Intent intent = new Intent(this, OrderActivity.class);
                    startActivity(intent);

                    finish();
                })
                .setNegativeButton("NO", (dialog, which) -> {
                    dialog.dismiss();
                    cart.setQuantity(0);
                    stateChange();
                })
                .show();
    }

    private void cartFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void stateChange() {
        int i_quantity = cart.getQuantity();
        double d_total = cart.getPrice() * ((double) i_quantity);
        String s_total = String.format(Locale.getDefault(), "ADD TO CART (RM %.2f)", d_total);

        binding.textColor.setText(cart.getChoose());
        binding.textQuantity.setText(String.valueOf(i_quantity));
        binding.buttonAdd.setText(s_total);
    }
}