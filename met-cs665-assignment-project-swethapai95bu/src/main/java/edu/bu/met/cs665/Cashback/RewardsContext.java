package edu.bu.met.cs665.Cashback;


/**
 * Context class for generating rewards
 */
public class RewardsContext {

    public double getRewards(Cashback cb, double amt) {
        double rewards = cb.getCashback(amt);
        return rewards;
    }
}
