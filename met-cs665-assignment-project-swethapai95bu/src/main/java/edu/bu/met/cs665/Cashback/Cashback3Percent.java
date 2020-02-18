package edu.bu.met.cs665.Cashback;


/**
 * Class used to generate 3% cashback on selected category
 */
public class Cashback3Percent implements Cashback {

    /**
     * Returns 3% of the amount spent
     */
    @Override
    public double getCashback(double amt_spent) {
        double rewards = 0.03*amt_spent;
        return rewards;
    }
}
