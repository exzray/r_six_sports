package com.nomi.rsixports.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.utility.Shared;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private final MutableLiveData<DocumentSnapshot> data = new MutableLiveData<>();


    public LiveData<DocumentSnapshot> getData() {
        load();
        return data;
    }

    private void load() {
        Shared
                .getProfileDocumentReference()
                .get()
                .addOnSuccessListener(data::setValue)
                .addOnFailureListener(e -> Log.i(TAG, e.getMessage()));
    }
}
