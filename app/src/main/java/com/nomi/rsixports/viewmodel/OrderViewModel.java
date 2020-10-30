package com.nomi.rsixports.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.nomi.rsixports.utility.Shared;

import java.util.List;

public class OrderViewModel extends ViewModel {

    private static final String TAG = "OrderViewModel";

    private final MutableLiveData<DocumentSnapshot> data = new MutableLiveData<>();
    private final MutableLiveData<List<DocumentSnapshot>> list = new MutableLiveData<>();


    public LiveData<DocumentSnapshot> getData() {
        return data;
    }

    public LiveData<List<DocumentSnapshot>> getList() {
        return list;
    }

    public void findData(String id) {
        Shared
                .getOrderCollectionReference()
                .document(id)
                .get()
                .addOnSuccessListener(data::setValue)
                .addOnFailureListener(e -> Log.i(TAG, e.getMessage()));
    }

    public void findList() {
        Shared
                .getOrderCollectionReference()
                .orderBy("delivered", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(query -> list.setValue(query.getDocuments()))
                .addOnFailureListener(e -> Log.i(TAG, e.getMessage()));
    }
}
