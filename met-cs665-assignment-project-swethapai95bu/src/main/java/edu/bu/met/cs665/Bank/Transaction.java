package edu.bu.met.cs665.Bank;

import java.sql.Timestamp;

public class Transaction {

    String place;
    double amount;
    Timestamp timestamp;

    public Transaction(String place, double amount, Timestamp timestamp) {
        this.place = place;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getPlace() {
        return place;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
