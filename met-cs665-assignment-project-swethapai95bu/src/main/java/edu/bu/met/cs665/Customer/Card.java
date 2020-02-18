package edu.bu.met.cs665.Customer;

import edu.bu.met.cs665.Places.Place;

/**
 * Interface representing a card
 * The interface has been implemented by the DebitCard and CreditCard classes
 * Uses the bridge pattern
 */
public interface Card {

        public void swipe(Place p, double bill);
}
