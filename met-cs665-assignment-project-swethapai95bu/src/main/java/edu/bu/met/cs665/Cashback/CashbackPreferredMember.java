package edu.bu.met.cs665.Cashback;


/**
 * Class to generate cashback for preferred members
 */
public class CashbackPreferredMember implements Cashback {

    /**
     * Returns 3.75% cashback to preferred members as rewards
     */
    @Override
    public double getCashback(double amt_spent) {
        double rewards = 0.0375*amt_spent;
        return rewards;
    }
}
