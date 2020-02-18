package edu.bu.met.cs665.Places;

import edu.bu.met.cs665.Customer.AcceptInterface;
import edu.bu.met.cs665.Customer.VisitorImpl;
import edu.bu.met.cs665.Customer.VisitorInterface;

import java.util.Random;

/**
 * Abstract class which represents a place that allows card payment
 */
public abstract class Place {

//    public abstract double processPayment(AcceptInterface ai, double bill);

    /**
     * Method to generate a bill
     */
    public double generateBill() {

        System.out.println("-----------------------CARD TRANSACTION---------------------------------");
        Random rand = new Random();
        int bill_amt = rand.nextInt(200);
        System.out.println("The bill amount is "+bill_amt);
        return bill_amt;
    }

    /**
     *
     * Method to process card payment
     */
    public double processPayment(AcceptInterface a, double bill_amt) {
        //Use the visitor to get the balance on the card swiped
        VisitorInterface vi = new VisitorImpl();
        double balance = a.accept(vi);
        System.out.println("Balance in the card is " + balance);

        if (balance > bill_amt) {
            return bill_amt;
        } else {
            System.out.println("The payment did not go through due to insufficient balance");
            return 0;

        }
    }
}
