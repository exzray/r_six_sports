package com.nomi.rsixports.model;

import java.util.Date;

public class CategoryModel {

    private String label = "";

    private Date created = new Date();


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
