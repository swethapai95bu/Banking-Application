package edu.bu.met.cs665.Customer;

import edu.bu.met.cs665.Bank.CashbackCategory;

import edu.bu.met.cs665.Places.Place;

/**
 * Class that represents a credit card
 */
public class CreditCard extends Observer implements Card, AcceptInterface {

    //Card number
    long card_no;
    //Card expiry date
    String expiry;
    //Card cvv
    int cvv;
    //Credit limit on the card
    double creditLimit;

    /**
     * Constructor to create a credit card
     */
    public CreditCard(long card_no, String expiry, int cvv, double creditLimit, Subject subject) {
        this.card_no = card_no;
        this.expiry = expiry;
        this.cvv = cvv;
        this.creditLimit = creditLimit;

        this.subject = subject;
        this.subject.add(this);
    }

    /**
     * Method called to swipe a credit card at a restaurant, gas station etc.
     */
    @Override
    public void swipe(Place p, double bill) {
        System.out.println("Credit Card swiped");
        p.processPayment(this, bill);
    }

    /**
     *
     * Accept method of the visitor pattern which calls visit() to return the creditLimit
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
        //Decrease the credit limit if the card is swiped
        if(subject.getState()==1)
            return;
        else {
            creditLimit = creditLimit - amt;
            System.out.println("Credit Limit is now " + creditLimit);
        }
    }

}
