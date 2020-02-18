package edu.bu.met.cs665.Customer;


/**
 * Class which implements the methods of VisitorInterface
 */
public class VisitorImpl implements VisitorInterface {

    /**
     * Method to return the credit limit when the CreditCard class is visited
     */
    @Override
    public double visit(CreditCard cc) {
        return cc.creditLimit;
    }

    /**
     * Method to return the balance in the checkings account when the DebitCard class is visited
     */
    @Override
    public double visit(DebitCard dc) {
        return dc.balance;
    }
}
