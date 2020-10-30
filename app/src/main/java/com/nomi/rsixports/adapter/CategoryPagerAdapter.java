package com.nomi.rsixports.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.fragment.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private final List<CategoryFragment> fragments = new ArrayList<>();


    public CategoryPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void update(List<DocumentSnapshot> snapshots) {
        fragments.clear();

        for (DocumentSnapshot snapshot : snapshots)
            fragments.add(CategoryFragment.newInstance(snapshot));

        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getCategoryLabel();
    }
}
