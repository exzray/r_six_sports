package com.nomi.rsixports.model;

import com.google.firebase.firestore.Exclude;

import java.text.DateFormat;
import java.util.Date;

public class OrderModel {

    public enum METHOD {CASH, DEBIT}

    public enum STATUS {PENDING, CANCEL, APPROVE, COMPLETE}


    private String auth_uid = "";

    private String name = "";
    private String contact = "";
    private String address = "";

    private METHOD payment = METHOD.CASH;
    private STATUS status = STATUS.PENDING;

    private Date created = new Date();
    private Date delivered = new Date();


    public String getAuth_uid() {
        return auth_uid;
    }

    public void setAuth_uid(String auth_uid) {
        this.auth_uid = auth_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public METHOD getPayment() {
        return payment;
    }

    public void setPayment(METHOD payment) {
        this.payment = payment;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    @Exclude
    public String getStringDelivered() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(getDelivered());
    }
}
