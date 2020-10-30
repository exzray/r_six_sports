package com.nomi.rsixports.viewmodel;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.nomi.rsixports.utility.Shared;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    private static final String TAG = "CategoryViewModel";

    private final MutableLiveData<List<DocumentSnapshot>> data = new MutableLiveData<>();


    public LiveData<List<DocumentSnapshot>> getData() {
        load();
        return data;
    }

    private void load() {
        if (registration != null) return;

        final CollectionReference categoryRef = Shared.getCategoryCollectionReference();
        registration = categoryRef.addSnapshotListener((value, error) -> {
            error(error);
            value(value);
        });
    }

    private void value(@Nullable QuerySnapshot value) {
        if (value == null) return;
        data.setValue(value.getDocuments());
    }

    private void error(@Nullable FirebaseFirestoreException error) {
        if (error == null) return;
        Log.i(TAG, error.getMessage());
    }

    private ListenerRegistration registration;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (registration != null) registration.remove();
    }
}
