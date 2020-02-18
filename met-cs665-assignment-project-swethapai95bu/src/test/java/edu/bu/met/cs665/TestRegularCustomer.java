package edu.bu.met.cs665;

import edu.bu.met.cs665.Bank.BankApplication;
import edu.bu.met.cs665.Bank.CashbackCategory;
import edu.bu.met.cs665.Bank.CustomerAccount;
import edu.bu.met.cs665.Customer.CreditCard;
import edu.bu.met.cs665.Customer.DebitCard;
import edu.bu.met.cs665.Customer.Subject;
import edu.bu.met.cs665.Places.*;
import org.junit.Test;

public class TestRegularCustomer {

    @Test
    public void testTransactions() {
        //State for credit card transactions
        final int CC = 2;
        final int DC = 1;

        //Application to create bank accounts for the customer
        BankApplication bankApplication = new BankApplication("Walter White", "857-472-6588", "128 Kelton St.", "heisenberg@gmail.com", 60, 17800, 1300);
        //Check if the application was accepted by the bank
        Boolean accepted = bankApplication.createAccount();

        if(accepted) {

            //Get credit limit based on deposit to savings account
            int creditLimit = bankApplication.getCreditLimit();
            //Get credit score
            int ficoScore = bankApplication.getCreditScore();
            //Defines if the member is a preferred member
            Boolean isPreferred = bankApplication.isPreferredMember();
            //Get cashback category assigned to the member
            CashbackCategory choice = bankApplication.getCashbackCategory();

            //Subject of the observer pattern
            Subject sub = new Subject();

            //Example of Builder Pattern
            CustomerAccount acc115 = new CustomerAccount.AccountBuilder(bankApplication.name, bankApplication.age, sub).withCheckingDeposit(bankApplication.checking_deposit).withSavingsDeposit(bankApplication.savings_deposit).withCreditLimit(creditLimit).withCreditScore(ficoScore).withCashbackCategory(choice).isPreferredMember(isPreferred).build();
            System.out.println(acc115.toString());

            //Assigning credit and debit cards to the customer and attaching them as observers to the subject
            acc115.assignCreditCard(new CreditCard(749837498, "04/23", 900, creditLimit, sub ));
            acc115.assignDebitCard(new DebitCard(874504750, "19/29", 344, bankApplication.checking_deposit, sub));


            /**
             * Debit card transaction at the pharmacy
             */
            Drugstore central_pharmacy = new Drugstore();
            double pharmacy_bill = central_pharmacy.generateBill();
            //Example of bridge pattern
            acc115.getDebitCard().swipe(central_pharmacy, pharmacy_bill);
            //Set state to 1 if debit card is swiped and 2 if credit card is swiped
            sub.setState(DC, pharmacy_bill, Drugstore.type_of_place);

            /**
             * Credit card transaction at an online store
             */
            OnlineStore amazon = new OnlineStore();
            double online_bill = amazon.generateBill();
            acc115.getCreditCard().swipe(amazon,online_bill );
            sub.setState(CC, online_bill, OnlineStore.type_of_place);

            /**
             * Credit card transaction for dining
             */
            FastFoodChain mcDonalds = new FastFoodChain();
            double food_bill = mcDonalds.generateBill();
            acc115.getCreditCard().swipe(mcDonalds, food_bill);
            sub.setState(CC, food_bill, FastFoodChain.type_of_place);

            /**
             * Debit card transaction at the grocery store
             */
            GroceryStore gc = new GroceryStore();
            double grocery_bill = gc.generateBill();
            acc115.getDebitCard().swipe(gc, grocery_bill);
            sub.setState(DC, grocery_bill, GroceryStore.type_of_place);

            /**
             * Credit card transaction for Gas
             */
            GasStation shell = new GasStation();
            double gas_bill =shell.generateBill();
            acc115.getCreditCard().swipe(shell, gas_bill);
            sub.setState(CC, gas_bill, GasStation.type_of_place);

            //Changing the cashback category
            acc115.changeCashbackCategory(CashbackCategory.GAS);

            /**
             * Credit card transaction for Gas
             */
            double bill_gas = shell.generateBill();
            acc115.getCreditCard().swipe(shell, bill_gas);
            sub.setState(CC, bill_gas, GasStation.type_of_place);

            /**
             * Deposit and withdrawal
             */
            acc115.depositToCheckings(500);
            acc115.depositToSavings(5000);
            acc115.withdraw(210);

            /**
             * Debit card transaction for dining
             */
            FastFoodChain fiveGuys = new FastFoodChain();
            double f_bill = fiveGuys.generateBill();
            acc115.getDebitCard().swipe(fiveGuys, f_bill);
            sub.setState(DC, f_bill, FastFoodChain.type_of_place);

            /**
             * Credit card transaction at the pharmacy
             */
            Drugstore cvs = new Drugstore();
            double drugs = cvs.generateBill();
            acc115.getCreditCard().swipe(cvs, drugs);
            sub.setState(CC, drugs, Drugstore.type_of_place);

            /**
             * Credit card transaction for Gas
             */
            GasStation exxon = new GasStation();
            double b_gas = exxon.generateBill();
            acc115.getCreditCard().swipe(exxon, bill_gas);
            sub.setState(CC, b_gas, GasStation.type_of_place);

            /**
             * Paying off credit card
             */
            acc115.payOffCreditCard();

            /**
             * Redeeming rewards
             */
            acc115.redeemRewards();

            /**
             * Viewing transactions
             */
            acc115.getTransactions();

            System.out.println(acc115.toString());
        }
    }
}
