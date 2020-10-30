package com.nomi.rsixports.utility;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nomi.rsixports.model.CartModel;
import com.nomi.rsixports.model.CategoryModel;
import com.nomi.rsixports.model.ConfigModel;
import com.nomi.rsixports.model.OrderModel;
import com.nomi.rsixports.model.ProductModel;
import com.nomi.rsixports.model.ProfileModel;

public class Helper {

    public static CategoryModel snapshotToCategory(DocumentSnapshot snapshot) {
        return snapshot.toObject(CategoryModel.class);
    }

    public static ProductModel snapshotToProduct(DocumentSnapshot snapshot) {
        return snapshot.toObject(ProductModel.class);
    }

    public static CartModel snapshotToCart(DocumentSnapshot snapshot) {
        return snapshot.toObject(CartModel.class);
    }

    public static OrderModel snapshotToOrder(DocumentSnapshot snapshot) {
        return snapshot.toObject(OrderModel.class);
    }

    public static ProfileModel snapshotToProfile(DocumentSnapshot snapshot) {
        return snapshot.toObject(ProfileModel.class);
    }

    public static ConfigModel snapshotToConfig(DocumentSnapshot snapshot) {
        return snapshot.toObject(ConfigModel.class);
    }
}
