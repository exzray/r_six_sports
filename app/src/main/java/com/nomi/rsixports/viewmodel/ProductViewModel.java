package com.nomi.rsixports.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.utility.Shared;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private static final String TAG = "ProductViewModel";

    private final MutableLiveData<DocumentSnapshot> product = new MutableLiveData<>();
    private final MutableLiveData<List<DocumentSnapshot>> productByCategory = new MutableLiveData<>();


    public LiveData<DocumentSnapshot> getProduct() {
        return product;
    }

    public LiveData<List<DocumentSnapshot>> getProductByCategory() {
        return productByCategory;
    }

    public void setProduct(String uid) {
        Shared
                .getProductCollectionReference()
                .document(uid)
                .get()
                .addOnSuccessListener(product::setValue)
                .addOnFailureListener(e -> Log.i(TAG, e.getMessage()));
    }

    public void setProductByCategory(String uid) {
        Shared
                .getProductCollectionReference()
                .whereEqualTo(Shared.FIELD_CATEGORY_UID, uid)
                .get()
                .addOnSuccessListener(query -> productByCategory.setValue(query.getDocuments()))
                .addOnFailureListener(e -> Log.i(TAG, e.getMessage()));
    }
}
