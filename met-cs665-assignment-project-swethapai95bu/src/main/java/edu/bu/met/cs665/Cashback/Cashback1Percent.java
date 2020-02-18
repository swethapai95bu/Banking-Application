package edu.bu.met.cs665.Cashback;


/**
 * Class to generate 1% cashback
 */
public class Cashback1Percent implements Cashback {

    /**
     *
     * Returns 1% of the amount spent as rewards
     */
    @Override
    public double getCashback(double amt_spent) {
        double rewards = 0.01*amt_spent;
        return rewards;
    }
}
