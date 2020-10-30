package com.nomi.rsixports.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nomi.rsixports.databinding.ItemImageBinding;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.VH> {

    private final List<String> images = new ArrayList<>();


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemImageBinding binding = ItemImageBinding.inflate(inflater, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setData(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void update(List<String> list) {
        images.clear();
        images.addAll(list);
        notifyDataSetChanged();
    }


    static class VH extends RecyclerView.ViewHolder {

        private final ItemImageBinding binding;


        public VH(ItemImageBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void setData(String s) {
            final Context context = binding.getRoot().getContext();

            Glide.with(context).load(s).into(binding.image);
        }
    }
}
