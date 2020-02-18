package edu.bu.met.cs665.Customer;

import edu.bu.met.cs665.Bank.CashbackCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject of the observer pattern
 */
public class Subject {
    //Observers attached to the subject
    private List<Observer> observers = new ArrayList<>();
    //State which triggers an update
    private int state;


    /**
     * Method to add an observer to the observes list
     */
    public void add(Observer o) {
        observers.add(o);
    }

    /**
     * Method to detach an observer from the observers list
     */
    public void remove(Observer o) {
        observers.remove(o);
    }

    /**
     * Returns the state
     */
    public int getState() {
        return state;
    }

    /**
     * Method to set the state
     * This in turn calls the execute() method
     */
    public void setState(int value, double amt, CashbackCategory place) {
        this.state = value;
        execute(amt, place);
    }

    /**
     * Method which calls the update methods of all the observers attached to the subject
     */
    private void execute(double amt, CashbackCategory place) {
        for (Observer observer : observers) {
            observer.update(amt, place);
        }
    }
}