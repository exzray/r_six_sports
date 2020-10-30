package com.nomi.rsixports.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.custom.SnapshotItemClickListener;
import com.nomi.rsixports.databinding.ItemProductBinding;
import com.nomi.rsixports.model.ProductModel;
import com.nomi.rsixports.utility.Helper;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    private final List<DocumentSnapshot> snapshots = new ArrayList<>();
    private final SnapshotItemClickListener listener;


    public ProductAdapter(SnapshotItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductBinding binding = ItemProductBinding.inflate(inflater, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(snapshots.get(position));
    }

    @Override
    public int getItemCount() {
        return snapshots.size();
    }

    public void update(List<DocumentSnapshot> snapshots) {
        this.snapshots.clear();
        this.snapshots.addAll(snapshots);
        notifyDataSetChanged();
    }


    class VH extends RecyclerView.ViewHolder {

        private final ItemProductBinding binding;


        public VH(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(DocumentSnapshot snapshot) {
            itemView.setOnClickListener(v -> listener.click(snapshot));

            final ProductModel data = Helper.snapshotToProduct(snapshot);

            binding.textName.setText(data.getName());
            binding.textBrand.setText(data.getBrand());
            binding.textPrice.setText(data.getPriceString());

            Glide.with(itemView).load(data.getImages().get(0)).into(binding.image);
        }
    }
}
