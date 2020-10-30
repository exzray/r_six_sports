package com.nomi.rsixports.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.custom.SnapshotItemClickListener;
import com.nomi.rsixports.databinding.ItemHistoryBinding;
import com.nomi.rsixports.model.OrderModel;
import com.nomi.rsixports.utility.Helper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.VH> {

    private final SnapshotItemClickListener listener;
    private final List<DocumentSnapshot> snapshots = new ArrayList<>();


    public HistoryAdapter(SnapshotItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(inflater);
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

        private final ItemHistoryBinding binding;


        public VH(ItemHistoryBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void setData(DocumentSnapshot snapshot) {
            OrderModel data = Helper.snapshotToOrder(snapshot);

            String s_delivered = "Deliver date:\t\t" + DateFormat.getDateInstance(DateFormat.MEDIUM).format(data.getDelivered());
            String s_status = data.getStatus().toString();

            binding.textDate.setText(s_delivered);
            binding.textStatus.setText(s_status);
            binding.buttonDelete.setOnClickListener(v -> listener.click(snapshot));
        }
    }
}
