package edu.bu.met.cs665;

import edu.bu.met.cs665.Bank.BankApplication;
import edu.bu.met.cs665.Bank.CashbackCategory;
import edu.bu.met.cs665.Bank.CustomerAccount;
import edu.bu.met.cs665.Customer.CreditCard;
import edu.bu.met.cs665.Customer.DebitCard;
import edu.bu.met.cs665.Customer.Subject;
import edu.bu.met.cs665.Places.GasStation;
import edu.bu.met.cs665.Places.Restaurant;
import org.junit.Test;

public class TestPreferredMember {

    @Test
    public void testAllFunctionalities() {
        //State for credit card transactions
        final int CC = 2;
        final int DC = 1;

        //Creating a bank application
        BankApplication bankApplication = new BankApplication("Skyler", "857-472-4988", "128 Kelton St.", "wskyler@gmail.com", 52, 35000, 2000);
        Boolean accepted = bankApplication.createAccount();

        //If the bank application is accepted, create accounts and allow user to perform transactions
        if(accepted) {

            //Generate creditLimit, creditScore and cashback category for the customer
            int creditLimit = bankApplication.getCreditLimit();
            int ficoScore = bankApplication.getCreditScore();
            Boolean isPreferred = bankApplication.isPreferredMember();
            CashbackCategory choice = bankApplication.getCashbackCategory();

            Subject sub = new Subject();

            //Create Customer Account using builder pattern
            CustomerAccount acc = new CustomerAccount.AccountBuilder(bankApplication.name, bankApplication.age, sub).withCheckingDeposit(bankApplication.checking_deposit).withSavingsDeposit(bankApplication.savings_deposit).withCreditLimit(creditLimit).withCreditScore(ficoScore).withCashbackCategory(choice).isPreferredMember(isPreferred).build();

            System.out.println(acc.toString());


            //Assign credit and debit cards to the customer and attach it as observers to the subject
            acc.assignCreditCard(new CreditCard(928491707, "07/23", 655, creditLimit, sub ));
            acc.assignDebitCard(new DebitCard(3948093, "09/21", 489, bankApplication.checking_deposit, sub));

            //Change the cashback category of the customer
            acc.changeCashbackCategory(CashbackCategory.DINING);

            /**
             * Credit card transaction at for dining
             */
            Restaurant pizzaHut = new Restaurant();
            double amtToBePaid = pizzaHut.generateBill();
            acc.getCreditCard().swipe(pizzaHut, amtToBePaid);
            //Observer Pattern
            //Set state to 1 if debit card is swiped and 2 if credit card is swiped
            sub.setState(CC, amtToBePaid, Restaurant.type_of_place);

            /**
             * Debit card transaction at the gas station
             */
            GasStation shell = new GasStation();
            double gas_bill = shell.generateBill();
            acc.getDebitCard().swipe(shell, gas_bill);
            sub.setState(DC, gas_bill, GasStation.type_of_place);


            /**
             * Credit card transaction for dining
             */
            double food_bill = pizzaHut.generateBill();
            acc.getCreditCard().swipe(pizzaHut, food_bill);
            sub.setState(2, food_bill, Restaurant.type_of_place);


            //Change the cashback category of the customer
            acc.changeCashbackCategory(CashbackCategory.GAS);

            /**
             * Deposit to accounts
             */
            acc.depositToCheckings(300);
            acc.depositToSavings(5800);

            /**
             * Withdraw from checkings
             */
            acc.withdraw(2000);

            /**
             * Pay off credit card
             */
            acc.payOffCreditCard();

            /**
             * Redeeming cashback
             */
            acc.redeemRewards();

            /**
             * View all transactions
             */
            acc.getTransactions();

            /**
             * Example of card getting declined due to low balance
             */
            System.out.println("-------------------CARD DECLINED EXAMPLE----------------------");
            acc.getDebitCard().swipe(shell, 1000);

        }
    }
}
