package com.example.mywedding;

public class Guest {
    private int id;
    private String guestName;
    private String gender;
    private String notes;
    private int status;
    private String phone;
    private String address;
    private String eMail;

    public Guest() {}

    public Guest(int id, String guestName, String gender, String notes, int status, String phone, String address, String eMail) {
        this.id = id;
        this.guestName = guestName;
        this.gender = gender;
        this.notes = notes;
        this.status = status;
        this.phone = phone;
        this.address = address;
        this.eMail = eMail;
    }

    public Guest(String guestName, String gender, String notes, int status, String phone, String address, String eMail) {
        this.guestName = guestName;
        this.gender = gender;
        this.notes = notes;
        this.status = status;
        this.phone = phone;
        this.address = address;
        this.eMail = eMail;
    }

    public Guest(int id, String guestName, String gender, String notes, String phone, String address, String eMail) {
        this.id = id;
        this.guestName = guestName;
        this.gender = gender;
        this.notes = notes;
        this.phone = phone;
        this.address = address;
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
