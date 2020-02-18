package edu.bu.met.cs665.Bank;

import edu.bu.met.cs665.Cashback.Cashback1Percent;
import edu.bu.met.cs665.Cashback.Cashback3Percent;
import edu.bu.met.cs665.Cashback.CashbackPreferredMember;
import edu.bu.met.cs665.Cashback.RewardsContext;
import edu.bu.met.cs665.Customer.CreditCard;
import edu.bu.met.cs665.Customer.DebitCard;
import edu.bu.met.cs665.Customer.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents bank accounts of a customer
 * Variables of this class are initialized using a Builder
 */
public final class CustomerAccount extends Observer {

    //Variables to store details of the customers
    private String name;
    private int age;
    private double savings;
    private double checking;
    private double creditLimit;
    private int ficoScore;
    private Boolean preferred_member;
    private CashbackCategory choice;
    private Subject subject;
    private DebitCard debitCard;
    private CreditCard creditCard;
    private double rewards;
    private List<Transaction> transactions = new ArrayList<Transaction>();
    private double creditLimit_original;

    /**
     * Constructor which uses an instance of the Builder to set values to variables
     */
    private CustomerAccount(AccountBuilder ab) {
        this.name = ab.name;
        this.age = ab.age;
        this.savings = ab.savings;
        this.checking = ab.checking;
        this.creditLimit = ab.creditLimit;
        this.ficoScore = ab.ficoScore;
        this.choice = ab.choice;
        this.preferred_member = ab.preferred_member;
        this.creditLimit_original = ab.creditLimit;
        this.subject = ab.subject;
        this.subject.add(this);
    }

    /**
     * Returns debit card associated with a customer
     */
    public DebitCard getDebitCard() {
        return debitCard;
    }

    /**
     * Returns credit card associated with a customer
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }


    @Override
    public String toString() {
        return "CustomerAccount{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", savings=" + savings +
                ", checking=" + checking +
                ", creditLimit=" + creditLimit +
                ", ficoScore=" + ficoScore +
                ", preferred_member=" + preferred_member +
                ", choice=" + choice +
                '}';
    }

    /**
     * Method to assign debit card to a customer
     */
    public void assignDebitCard(DebitCard dcard) {
        this.debitCard = dcard;
    }

    /**
     * Method to assign credit card to a customer
     */
    public void assignCreditCard(CreditCard cc) {

        this.creditCard = cc;
    }

    /**
     * Observer method which is called when a card is swiped
     */
    @Override
    public void update(double amt, CashbackCategory place) {

        //Get the state of the subject and update the required account accordingly
        //Update the checkings account if the state is 1
        if(subject.getState()==1) {
            checking = checking - amt;
            //System.out.println("Balance in checkings account is now " + checking);
        }
        //Update the credit limit if the state is 2
        else if(subject.getState()==2) {
            creditLimit = creditLimit-amt;

            RewardsContext rc = new RewardsContext();
            double cashback_amt;

            //Check if the card was swiped at a place where the customer chose to get 3% cashback
            // If yes, generate cashback and add it to rewards
            if(place==choice) {

                if(preferred_member)
                    cashback_amt = rc.getRewards(new CashbackPreferredMember(), amt);
                else
                    cashback_amt = rc.getRewards(new Cashback3Percent(), amt);

                rewards = rewards + cashback_amt;


            }
            else {
                cashback_amt = rc.getRewards(new Cashback1Percent(), amt);
                rewards = rewards+cashback_amt;
            }
            System.out.println("Rewards are now "+rewards);

            //Increase ficoScore if credit card is swiped
            ficoScore = ficoScore +2;

        }
        else
            System.out.println("Value of state not recognized");

        //Add the transaction to a list
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Transaction t = new Transaction(place.toString(), amt, ts);
        transactions.add(t);
    }

    /**
     * Method to view all previous transactions
     * Includes transactions on both debit and credit card
     */
    public void getTransactions() {

        System.out.println("-------------------------SHOWING CARD TRANSACTIONS----------------------------");

        System.out.println("Place"+"\t\t"+"Amount"+"\tDate");

        for(Transaction t: transactions) {
            System.out.println(t.getPlace()+"\t\t"+t.getAmount()+"\t"+t.getTimestamp());
        }



    }

    /**
     * Method to change the cashback category
     */
    public void changeCashbackCategory(CashbackCategory choice) {
        System.out.println("---------------------CHANGING CASHBACK CATEGORY----------------------------");
        this.choice = choice;
        System.out.println(toString());
    }

    /**
     * Method to redeem the rewards and add it to the checkings account
     */
    public void redeemRewards() {
        System.out.println("-------------------------REDEEMING REWARDS----------------------------");
        System.out.println("Redeeming an amount of to checkings account "+rewards);
        checking = checking + rewards;
        rewards = 0;
        System.out.println("Balance in checkings account is now "+checking);

    }


    /**
     * Method to withdraw money from the checkings account
     */
    public void withdraw(double amt) {
        System.out.println("-------------------------WITHDRAWAL----------------------------");
        if (checking>amt) {
            checking = checking - amt;
            System.out.println("Balance in checkings account is now "+checking);
            //Update the debit card
            getDebitCard().balance = checking;
        }
        else
            System.out.println("Insufficient Balance");

    }

    /**
     * Method to deposit money to the checkings account
     */
    public void depositToCheckings(double amt) {
        System.out.println("-------------------------DEPOSIT------------------------------");
        checking = checking+amt;
        System.out.println("Balance in checkings account is now "+checking);
        getDebitCard().balance = checking;

    }

    /**
     * Method to deposit money to the savings account
     */
    public void depositToSavings(double amt) {
        System.out.println("-------------------------DEPOSIT------------------------------");
        savings = savings +amt;
        System.out.println("Balance in savings account is now "+savings);

    }

    /**
     * Method to pay off credit card and set the credit limit to the original amount
     */
    public void payOffCreditCard() {
        System.out.println("-----------------------PAY OFF CREDIT CARD------------------------------");
        double amt_to_pay = creditLimit_original - creditLimit;

        //Check if the savings account has enough balance to pay off the credit card
        if(savings>amt_to_pay) {
            savings = savings - amt_to_pay;
            creditLimit = creditLimit_original;
            System.out.println("Balance in savings is now "+savings);
            System.out.println("Credit limit is back to "+creditLimit);
        }
        else
            System.out.println("Insufficient balance in the savings account to pay off the credit card. Please deposit money before making the transfer.");

    }

    /**
     * This class is a builder and is used to initialize variables of the CustomerAccount class
     */
    public static class AccountBuilder {

        private String name;
        private int age;
        private double savings;
        private double checking;

        private int creditLimit;
        private int ficoScore;
        private Boolean preferred_member;
        private CashbackCategory choice;
        private Subject subject;

        /**
         * Constructor to initialize name, age of the customer and subject which the observer has to be attached to
         */
        public AccountBuilder(String name, int age, Subject sub) {
            this.name = name;
            this.age = age;

            this.subject = sub;
        }

        /**
         * Method to deposit money into the savings account
         */
        public AccountBuilder withSavingsDeposit(double savings) {
            this.savings = savings;
            return this;
        }

        /**
         * Method to deposit money into the checkings account
         */
        public AccountBuilder withCheckingDeposit(double checking) {
            this.checking = checking;
            return this;
        }

        /**
         * Method to assign a credit limit to the customer
         */
        public AccountBuilder withCreditLimit(int creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        /**
         * Method to assign a cashback category to the customer
         */
        public AccountBuilder withCashbackCategory(CashbackCategory choice) {
            this.choice = choice;
            return this;
        }

        /**
         * Method to assign a default credit score to customer
         */
        public AccountBuilder withCreditScore(int ficoScore) {
            this.ficoScore = ficoScore;
            return this;
        }

        /**
         * Method to declare if the member is a preferred member
         */
        public AccountBuilder isPreferredMember(Boolean preferred_member) {
            this.preferred_member = preferred_member;
            return this;
        }

        /**
         * Builder method to instantiate the CustomerAccount
         */
        public CustomerAccount build() {

            return new CustomerAccount(this);
        }



    }



}
