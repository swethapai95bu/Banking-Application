package edu.bu.met.cs665.Customer;

import edu.bu.met.cs665.Bank.CashbackCategory;

/**
 * Observer class which declares the update() method
 */
public abstract class Observer {
    protected Subject subject;
    //Method called when the state of the subject changes
    public abstract void update(double amt, CashbackCategory place);
}