package edu.bu.met.cs665.Customer;


/**
 * Interface of the visitor pattern which declares the visit methods
 */
public interface VisitorInterface {

    double visit(CreditCard cc);
    double visit(DebitCard dc);

}
