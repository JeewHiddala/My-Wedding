package com.example.mywedding.Models;

public class BudgetModel {
    private int id;
    private String budgetName;
    private double amount;
    private String notes;
    private String category;
    private double paidAmount;
    private String paidDate;
    private String status;

    public BudgetModel(){
    }

    public BudgetModel(int id, String budgetName, double amount, String notes, String category, double paidAmount, String paidDate, String status) {
        this.id = id;
        this.budgetName = budgetName;
        this.amount = amount;
        this.notes = notes;
        this.category = category;
        this.paidAmount = paidAmount;
        this.paidDate = paidDate;
        this.status = status;
    }

    public BudgetModel(String budgetName, double amount, String notes, String category, double paidAmount, String paidDate, String status) {
        this.budgetName = budgetName;
        this.amount = amount;
        this.notes = notes;
        this.category = category;
        this.paidAmount = paidAmount;
        this.paidDate = paidDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }
}
