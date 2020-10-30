package com.nomi.rsixports.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.DetailActivity;
import com.nomi.rsixports.adapter.ProductAdapter;
import com.nomi.rsixports.databinding.FragmentCategoryBinding;
import com.nomi.rsixports.utility.Helper;
import com.nomi.rsixports.utility.Shared;
import com.nomi.rsixports.viewmodel.ProductViewModel;


public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private DocumentSnapshot snapshot_category;
    private ProductAdapter adapter;


    public static CategoryFragment newInstance(DocumentSnapshot snapshot) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.snapshot_category = snapshot;
        return fragment;
    }

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ProductAdapter(this::snapshotClick);

        ProductViewModel vm = new ViewModelProvider(this).get(ProductViewModel.class);
        vm.setProductByCategory(snapshot_category.getId());
        vm.getProductByCategory().observe(getViewLifecycleOwner(), snapshots -> adapter.update(snapshots));

        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    private void snapshotClick(DocumentSnapshot snapshot) {
        Intent intent = new Intent(requireContext(), DetailActivity.class);
        intent.putExtra(Shared.EXTRA_PRODUCT_UID, snapshot.getId());
        requireContext().startActivity(intent);
    }

    public String getCategoryLabel() {
        return Helper.snapshotToCategory(snapshot_category).getLabel();
    }
}