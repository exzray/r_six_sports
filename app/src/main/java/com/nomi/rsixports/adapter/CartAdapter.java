package com.nomi.rsixports.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.custom.SnapshotItemClickListener;
import com.nomi.rsixports.databinding.ItemCartBinding;
import com.nomi.rsixports.model.CartModel;
import com.nomi.rsixports.utility.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {

    private final SnapshotItemClickListener listener;
    private final List<DocumentSnapshot> snapshots = new ArrayList<>();


    public CartAdapter(SnapshotItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding binding = ItemCartBinding.inflate(inflater);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(snapshots.get(position), position);
    }

    @Override
    public int getItemCount() {
        return snapshots.size();
    }

    public void update(List<DocumentSnapshot> list) {
        snapshots.clear();
        snapshots.addAll(list);
        notifyDataSetChanged();
    }


    class VH extends RecyclerView.ViewHolder {

        private final ItemCartBinding binding;


        public VH(ItemCartBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void setData(DocumentSnapshot snapshot, int position) {
            CartModel data = Helper.snapshotToCart(snapshot);

            double total = data.getPrice() * (double) data.getQuantity();

            String s_description = String.format(Locale.getDefault(), "RM %.2f  x%d  RM %.2f", data.getPrice(), data.getQuantity(), total);
            String s_name = "" + (position + 1) + ")  ";

            if (!data.getChoose().isEmpty()) s_name += String.format(Locale.getDefault(), "%s #%s", data.getName(), data.getChoose());
            else s_name += data.getName();

            binding.textName.setText(s_name);
            binding.textDescription.setText(s_description);
            binding.buttonDelete.setOnClickListener(v -> listener.click(snapshot));
        }
    }
}
