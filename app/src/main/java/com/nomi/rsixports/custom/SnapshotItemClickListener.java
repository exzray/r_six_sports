package com.nomi.rsixports.custom;

import com.google.firebase.firestore.DocumentSnapshot;

public interface SnapshotItemClickListener {

    void click(DocumentSnapshot snapshot);
}
