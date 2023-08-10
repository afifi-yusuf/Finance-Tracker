package com.example.CS_IA_2;

public class Expense extends Cashflow{
    public Expense(double amount, String category, boolean isRecurring) {
        super(amount, category, isRecurring);
    }

    public String toString(){
        return "Cash outflow amount: " + Double.toString(this.getAmount()) + ", Category: " + this.getCategory() + ", Recurring: " + isRecurring();
    }

    @Override
    public java.lang.String getType() {
        return "Expense";
    }
}
