package edu.bu.met.cs665.Bank;

import java.util.Random;

/**
 * This class represents a bank application to create an account in the bank
 */
public class BankApplication {

    //Name of the customer
    public String name;
    //Phone number of the customer
    public String phone;
    //Address of the customer
    public String address;
    //Email ID of the customer
    public String email;
    //Age of the customer
    public int age;
    //Amount to be deposited in savings
    public double savings_deposit;
    //Amount to be deposited in checkings
    public double checking_deposit;

    /**
     *
     * Constructor used to obtain details of the customer who wants to open a bank account
     */
    public BankApplication(String name, String phone, String address, String email, int age, double savings_deposit, double checking_deposit) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.age = age;
        this.savings_deposit = savings_deposit;
        this.checking_deposit = checking_deposit;
    }

    /**
     * This method checks if the customer is eligible to open a bank account and return true if he/she is eligible
     */
    public Boolean createAccount() {

        //Check if the customer is above the age of 18 and has made a minimum deposit to both checkings and savings
        if(this.age>18 && this.savings_deposit>1500 && checking_deposit>0) {
            System.out.println("Bank account successfully created for the customer");
            return true;
        }

        return false;
    }

    /**
     * Method used to assign credit limit to the customer depending on their checkings deposit
     */
    public int getCreditLimit() {

        //Check balance in savings and assign a credit limit
        if (this.savings_deposit>0 && this.savings_deposit<2000)
            return 700;
        else if(this.savings_deposit>2000 && this.savings_deposit<10000)
            return 1800;
        else
            return 4000;

    }

    /**
     * Method to assign a credit score to customers
     */
    public int getCreditScore() {
        int default_score = 300;
        return default_score;
    }

    /**
     * Method to check if customer is eligible to be a preferred member
     */
    public Boolean isPreferredMember() {
        //Make the customer a preferred member if he/she maintains a high balance in the savings account
        if(this.savings_deposit>30000) {
            return true;
        }
        else
            return false;
    }

    /**
     * Method to assign a cashback category to the customers
     */
    public CashbackCategory getCashbackCategory() {

        //Get a random index and assign a cashback category randomly
        Random rand = new Random();
        int index = rand.nextInt(4);

        return CashbackCategory.values()[index];
    }


}
