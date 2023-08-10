package com.example.CS_IA_2;

import java.io.Serializable;

public class Cashflow implements Serializable {
    private double amount;
    private String category;
    private boolean isRecurring;
    public Cashflow(double amount, String category, boolean isRecurring){
        this.amount = amount;
        this.category = category;
        this.isRecurring = isRecurring;
    }
    public Cashflow() {
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
    public String toString(){
        return "";
    }
    public String getType(){
        return "";
    }
}
