package com.nomi.rsixports.model;

import com.google.firebase.firestore.Exclude;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderModel {

    public enum METHOD {CASH, DEBIT}

    public enum STATUS {PENDING, CANCEL, APPROVE, COMPLETE}


    private String auth_uid = "";

    private String name = "";
    private String contact = "";
    private String address = "";

    private Double order_pay = 0.0;
    private Double order_charge = 0.0;

    private Integer order_items = 0;

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

    public Double getOrder_pay() {
        return order_pay;
    }

    public void setOrder_pay(Double order_pay) {
        this.order_pay = order_pay;
    }

    public Double getOrder_charge() {
        return order_charge;
    }

    public void setOrder_charge(Double order_charge) {
        this.order_charge = order_charge;
    }

    public Integer getOrder_items() {
        return order_items;
    }

    public void setOrder_items(Integer order_items) {
        this.order_items = order_items;
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
    public String getStringPay() {
        return String.format(Locale.getDefault(), "RM %.2f", getOrder_pay());
    }

    @Exclude
    public String getStringCharge() {
        return String.format(Locale.getDefault(), "RM %.2f", getOrder_charge());
    }

    @Exclude
    public String getStringDelivered() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(getDelivered());
    }

    @Exclude
    public String getStringCreated() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(getCreated());
    }

    @Exclude
    public String getStringMethod() {
        return getPayment().toString();
    }

    @Exclude
    public String getStringStatus() {
        return getStatus().toString();
    }

    @Exclude
    public String getStringQuantity() {
        return String.valueOf(order_items);
    }
}
