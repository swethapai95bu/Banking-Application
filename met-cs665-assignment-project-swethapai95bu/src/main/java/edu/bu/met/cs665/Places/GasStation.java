package edu.bu.met.cs665.Places;

import edu.bu.met.cs665.Customer.AcceptInterface;
import edu.bu.met.cs665.Bank.CashbackCategory;

/**
 * This class represents a gas station which can process card payments
 */
public class GasStation extends Place {

    //Specifies the type of place
    static public final CashbackCategory type_of_place = CashbackCategory.GAS;

    /**
     * Method to process payment using a debit card or credit card
     */
    @Override
    public double processPayment(AcceptInterface a, double bill_amt) {

        System.out.println("Processing payment at Gas Station");
        double res = super.processPayment(a, bill_amt);
        return res;

    }
}
