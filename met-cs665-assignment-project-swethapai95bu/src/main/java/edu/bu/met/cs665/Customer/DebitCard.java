package edu.bu.met.cs665.Customer;

import edu.bu.met.cs665.Bank.CashbackCategory;
import edu.bu.met.cs665.Places.Place;

/**
 * Class that represents a debit card
 */
public class DebitCard extends Observer implements Card, AcceptInterface {

    //Card number
    long card_no;
    //Card expiry date
    String expiry;
    //Card cvv
    int cvv;
    //Balance in the checkings account
    public double balance;

    /**
     * Constructor to create a debit card
     */
    public DebitCard(long card_no, String expiry, int cvv, double balance, Subject subject) {
        this.card_no = card_no;
        this.cvv = cvv;
        this.expiry = expiry;
        this.balance = balance;

        this.subject = subject;
        this.subject.add(this);
    }


    /**
     * Method called to swipe a debit card at a restaurant, gas station etc.
     */
    @Override
    public void swipe(Place p, double bill) {
        System.out.println("Debit Card Swiped");
        p.processPayment(this, bill);


    }

    /**
     *
     * Accept method of the visitor pattern which calls visit() to return the balance on the debit card
     */
    @Override
    public double accept(VisitorInterface vi) {

        return vi.visit(this);
    }

    /**
     * Observer method called when the card is swiped
     */
    @Override
    public void update(double amt, CashbackCategory place) {
        //Decrease the balance if the card is swiped

        if (subject.getState()==2)
            return;
        else
        balance = balance - amt;
        System.out.println("Balance is now "+balance);
    }
}
