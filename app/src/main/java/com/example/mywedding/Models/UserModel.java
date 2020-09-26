package com.example.mywedding.Models;

public class UserModel {
    private int id;
    private String userName;
    private String userEmail;
    private String userContact;
    private String userStatus;
    private String partnerName;
    private String partnerEmail;
    private String partnerContact;
    private String partnerStatus;
    private String weddingName;
    private String weddingDate;

    public UserModel(int id, String userName, String userEmail, String userContact, String userStatus, String partnerName, String partnerEmail, String partnerContact, String partnerStatus, String weddingName, String weddingDate) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userContact = userContact;
        this.userStatus = userStatus;
        this.partnerName = partnerName;
        this.partnerEmail = partnerEmail;
        this.partnerContact = partnerContact;
        this.partnerStatus = partnerStatus;
        this.weddingName = weddingName;
        this.weddingDate = weddingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerEmail() {
        return partnerEmail;
    }

    public void setPartnerEmail(String partnerEmail) {
        this.partnerEmail = partnerEmail;
    }

    public String getPartnerContact() {
        return partnerContact;
    }

    public void setPartnerContact(String partnerContact) {
        this.partnerContact = partnerContact;
    }

    public String getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(String partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public String getWeddingName() {
        return weddingName;
    }

    public void setWeddingName(String weddingName) {
        this.weddingName = weddingName;
    }

    public String getWeddingDate() {
        return weddingDate;
    }

    public void setWeddingDate(String weddingDate) {
        this.weddingDate = weddingDate;
    }
}
