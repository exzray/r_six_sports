package com.nomi.rsixports.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.custom.SnapshotItemClickListener;
import com.nomi.rsixports.databinding.ItemOrderBinding;
import com.nomi.rsixports.model.OrderModel;
import com.nomi.rsixports.utility.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.VH> {

    private final SnapshotItemClickListener listener;
    private final List<DocumentSnapshot> snapshots;


    public OrderAdapter(SnapshotItemClickListener listener) {
        this.listener = listener;
        this.snapshots = new ArrayList<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOrderBinding binding = ItemOrderBinding.inflate(inflater);
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

    public void update(List<DocumentSnapshot> list) {
        snapshots.clear();
        snapshots.addAll(list);
        notifyDataSetChanged();
    }


    class VH extends RecyclerView.ViewHolder {

        private final ItemOrderBinding binding;

        public VH(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(DocumentSnapshot snapshot) {
            final OrderModel data = Helper.snapshotToOrder(snapshot);

            String s_name = data.getName();
            binding.textName.setText(s_name);

            String s_payment = String.format(Locale.getDefault(), "Payment Price RM %.2f", data.getOrder_pay());
            binding.textPayment.setText(s_payment);

            String s_items = String.format(Locale.getDefault(), "Items Count %d", data.getOrder_items());
            binding.textSize.setText(s_items);

            String s_status = data.getStatus().toString();
            binding.textStatus.setText(s_status);

            binding.buttonView.setOnClickListener(v -> listener.click(snapshot));
            binding.buttonCall.setOnClickListener(v -> call(data.getContact()));
        }

        private void call(String contact) {
            Context context = binding.getRoot().getContext();

            String s = "tel:" + contact;

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            context.startActivity(intent);
        }
    }
}
