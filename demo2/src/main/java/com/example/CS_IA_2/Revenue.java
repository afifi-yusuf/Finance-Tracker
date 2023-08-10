package com.example.CS_IA_2;

public class Revenue extends Cashflow{
    public Revenue(double amount, String category, boolean isRecurring) {
        super(amount, category, isRecurring);
    }

    public String toString(){
        return "Cash inflow amount: " + Double.toString(this.getAmount()) + ", Category: " + this.getCategory() + ", Recurring: " + isRecurring();
    }

    @Override
    public java.lang.String getType() {
        return "Revenue";
    }
}
