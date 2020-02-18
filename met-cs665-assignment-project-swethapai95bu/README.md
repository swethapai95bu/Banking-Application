# Term Project 

This project aims to build a Banking Application by implementing the Builder Pattern.


# Application description

The banking application aims to create bank accounts for customers who are eligible to hold accounts and also manage the credit card and debit card transactions of customers.

Following are some of the details of the application:

1. Bank application - Customers submit an application to the bank in order to create accounts. Accounts are of two types - Checkings and Savings. The bank determines if the customer is eligible to hold accounts in the bank depending on the age of the customer and amount deposited to the savings account. 
If the customer is eligible, bank accounts are created for the customer and credit cards and debit cards are assigned.

2. Credit Card - Every customer gets assigned a default credit score on creating an account. The credit limit assigned to every customer varies depending on the deposit in the savings account. Every customer gets cashback on using the credit card. Depending on the category, the customer might get 3% cashback or 1% cashback. On creation of the account, customers are randomly assigned a cashback category to receive 3% cashback e.g: 3% cashback on gas. This category can be changed later on by the customer to a category of his/her choice. If a customer deposits more than $30000 to their savings account, he/she becomes a preferred member and receives 3.75% cashback on selected category. The credit score of the customers increase everytime they use their credit card. 

3. Payment using cards - The debit card and credit card assigned to the customers can be used to pay bills at various places such as restaurants, gas stations, grocery stores, online stores and drugstores. When the debit card is used for payment, the bill amount is deducted from the checkings account if the customer has sufficient balance to make the payment. When the credit card is used to pay the bill, the amount is deducted from the credit limit. The credit score increases with increase in usage of the credit card. The customer also avails cashback on every payment which can be redeemed at any time. 

4. Customer Account - The customer account maintains the customer details, savings balance, checkings balance, credit limit, credit score, rewards, list of transactions etc. The customer can withdraw cash or deposit cash to the checkings/savings account. The customer can also redeem the rewards(cashback) and add it to their checkings account. He/she can pay off the credit card and reset the limit on the card. The customer account also allows the user to change their cashback category and view all the transactions made with their cards. 

5. Places with card payment - The application contains several classes which represent places that allow card payment such as restaurants, grocery stores, gas stations etc. These places generate a bill for the customer and process the payment using the cards. The required updates need to take place depending on whether a credit card or debit card is swiped. The card should be declined if the balance is lower than the bill amount. 

# Design patterns used

1. Builder pattern - The intent of the builder pattern is to separate the construction of a complex object from its representation so that the same construction process can create different representations. In this application, each customer has a different deposit amount, credit limit, cashback category etc. Builder pattern is useful in cases like these where there are several flavors of an object. An AccountBuilder has been used to help with creation of accounts for customers. The build method of the AccountBuilder is used to create objects of CustomerAccount. The parameter list could keep growing in the future if we want to add more features to the CustomerAccount.

2. Visitor pattern - This pattern has been used to get the credit limit from the CreditCard class and the balance from the DebitCard class. The AcceptInterface, CreditCard, DebitCard, VisitorInterface and VisitorImpl together make up the visitor pattern. The AcceptInterface has the accept() method which is overridden by CreditCard and DebitCard. The accept() method calls the visit() method of the VisitorImpl which returns the required value. 

3. Observer pattern - The observer pattern has been used to make the required changes in the DebitCard, CreditCard and CustomerAccount class when a card is swiped. When a card is swiped, the creditLimit has to be decreased if it is a credit card and the balance has to be deducted if it is a debit card. The CustomerAccount class should also reflect the same changes. The Subject class contains the state variable which calls the update() method of the observers on change. The CreditCard, DebitCard and CustomerAccount objects are the observers. The program determines which card was swiped depending on the value of the state and makes the necessary changes. When the credit card is swiped, the credit limit is decreased, the fico score is updated and the cashback is generated and added to rewards. 

4. Strategy pattern - This pattern has been used to generate cashback depending on the category of choice of the customer. The Cashback interface defines the getCashback() method which is overridden by the Cashback1Percent, Cashback3Percent and CashbackPreferredMember classes to return 1%, 3% and 3.75% cashback respectively. The RewardsContext class acts as the context and defines the getRewards() method which is used to pick one of the concrete strategies at run time. 

# Implementation description

The BankApplication object is used to create a bank application with details of the user and the amount they wish to deposit. If the customer is eligible, an account is created and debit/credit cards are assigned with which the user can perform the following actions:
* Withdraw cash from the bank account
* Deposit cash to the savings/checkings account
* Swipe the debit card 
* Swipe the credit card and avail cashback. This also increases the credit score of the customer. 
* Redeem the cashback and add it to the checkings account
* Pay off the credit card bill and reset the credit limit
* Change the cashback category to anything among gas, dining, groceries, onlineshopping and drugstores.
* View all the transactions made on the cards

Some of the advantages of the design of this application are:
* Flexibility - This design makes it very easy to add new features to the code. The builder pattern allows us to add more parameters without having to change the constructor if the class. More concrete strategies can be added to the strategy pattern without having to change much of the existing code. Places such as jewelry stores, book stores etc. can also be added to the application in the future. The program is highly scalable and flexible. 
* Robustness - The design is robust and can handle many different kinds of inputs. Transactions such as swiping a card or depositing an amount can be performed large number of times and the program will be able to handle it. 
* Understandability - The code is modular and utilizes many different design patterns which makes it easy to understand. The functionalities have been clearly divided among classes making it very simple.
* Reusability - Code for creating a bank account, swiping a card etc. can be reused by other applications with similar functionality. 

# How to compile the project

We use Apache Maven to compile and run this project. 

You need to install Apache Maven (https://maven.apache.org/)  on your system. 

Type on the command line: 

```bash
mvn clean compile
```

# How to create a binary runnable package 


```bash
mvn clean compile assembly:single
```


# How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
```

We recommand the above command for running the project. 

Alternativly, you can run the following command. It will generate a single jar file with all of the dependencies. 

```bash
mvn clean compile assembly:single

java -Dlog4j.configuration=file:log4j.properties -classpath ./target/JavaProjectTemplate-1.0-SNAPSHOT-jar-with-dependencies.jar  edu.bu.met.cs665.Main
```


# Run all the unit test classes.


```bash
mvn clean compile test

```

# Using Findbugs 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn findbugs:gui 
```

or 


```bash
mvn findbugs:findbugs
```


For more info about FindBugs see 

http://findbugs.sourceforge.net/

And about Maven Findbug plugin see 
https://gleclaire.github.io/findbugs-maven-plugin/index.html


You can install Findbugs Eclipse Plugin 

http://findbugs.sourceforge.net/manual/eclipse.html



SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.


# Run Checkstyle 

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. 
You can change it to other styles like sun checkstyle. 

To analyze this example using CheckStyle run 

```bash
mvn checkstyle:check
```

This will generate a report in XML format


```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser. 

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```


# Generate  coveralls:report 

```bash
mvn -DrepoToken=YOUR-REPO-TOCKEN-ON-COVERALLS  cobertura:cobertura coveralls:report
```


