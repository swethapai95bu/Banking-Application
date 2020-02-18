package edu.bu.met.cs665.Places;

import edu.bu.met.cs665.Customer.AcceptInterface;
import edu.bu.met.cs665.Bank.CashbackCategory;

/**
 * Represents a grocery store which accepts card payment
 */
public class GroceryStore extends Place {

    //Specifies the type of place
    static public final CashbackCategory type_of_place = CashbackCategory.GROCERIES;

    /**
     * Method to process payment using a debit card or credit card
     */
    @Override
    public double processPayment(AcceptInterface a, double bill_amt) {

        System.out.println("Processing payment at Grocery Store");
        double res = super.processPayment(a, bill_amt);
        return res;

    }

}
