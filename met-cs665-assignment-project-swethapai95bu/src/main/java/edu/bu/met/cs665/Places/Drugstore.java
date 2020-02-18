package edu.bu.met.cs665.Places;

import edu.bu.met.cs665.Customer.AcceptInterface;
import edu.bu.met.cs665.Bank.CashbackCategory;

/**
 * Class which represents a drug store accepting card payment
 */
public class Drugstore extends Place {

    //Specifies the type of place
    static public final CashbackCategory type_of_place = CashbackCategory.DRUGSTORES;

    /**
     * Method to process payment using a debit card or credit card
     */
    @Override
    public double processPayment(AcceptInterface a, double bill_amt) {

        System.out.println("Processing payment at Drug store");
        double res = super.processPayment(a, bill_amt);
        return res;


    }

}
