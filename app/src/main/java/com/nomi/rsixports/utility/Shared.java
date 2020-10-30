package com.nomi.rsixports.utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Shared {

    public static FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser getUser() {
        return getAuth().getCurrentUser();
    }

    public static FirebaseFirestore getFirestore() {
        return FirebaseFirestore.getInstance();
    }

    public static CollectionReference getCategoryCollectionReference() {
        return getFirestore().collection("category");
    }

    public static CollectionReference getProductCollectionReference() {
        return getFirestore().collection("product");
    }

    public static CollectionReference getOrderCollectionReference() {
        return getFirestore().collection("order");
    }

    public static CollectionReference getCartCollectionReference() {
        return getFirestore()
                .collection("account")
                .document(getUser().getUid())
                .collection("cart");
    }

    public static DocumentReference getProfileDocumentReference() {
        return getFirestore()
                .collection("account")
                .document(getUser().getUid());
    }


    public static final String EXTRA_PRODUCT_UID = "product_uid";

    public static final String FIELD_CATEGORY_UID = "category_uid";
}
