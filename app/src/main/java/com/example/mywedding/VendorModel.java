package com.example.mywedding;

public class VendorModel {
        private int id;
        private String vendorname,category,contactno,description,status,amount;

        public VendorModel(){

        }

        //constructor
        public VendorModel(int id, String vendorname, String category, String contactno, String description, String status, String amount) {
        this.id = id;
        this.vendorname = vendorname;
        this.category = category;
        this.contactno = contactno;
        this.description = description;
        this.status = status;
        this.amount = amount;
    }

    //constructor without id
    public VendorModel(String vendorname, String category, String contactno, String description, String status, String amount) {
        this.vendorname = vendorname;
        this.category = category;
        this.contactno = contactno;
        this.description = description;
        this.status = status;
        this.amount = amount;
    }

    //getter and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
