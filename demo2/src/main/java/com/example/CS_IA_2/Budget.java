package com.example.CS_IA_2;

public class Budget {
    private String category;
    private double budgetAmount;
    public Budget(String category, double budgetAmount){
        this.category = category;
        this.budgetAmount = budgetAmount;
    }

    public String getCategory() {
        return category;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }
}
