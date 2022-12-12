# ATM-MACHINE

this project implements the functionalities of an automated teller machine (ATM)

the ATM allows user to

1- Check balance inquiry

2- Withdraw funds

3- Deposit funds

this projects is made up of:

-class Screen whose function is to display messages on the ATM screen

-class Keypad receives input from the user, it serves as the ATM KEYPAD or (number keys)

-class Bankdatabase accesses the info in class Account only through class Account

-class Account stores user account details like Account Number, Pin, Total balance and Available balance. It also perform manipulations on the users account like debiting and crediting transactions and veriying pin, it also serves the only access to this functions to improve security

-class DepositSlot receives an envelope from the user for deposit, it return a boolean value if user inputs an envelope

-class Dispenser dispenses cash after any sucessful withdrawal transaction is performed

-class Transaction is a super class to classes Withdrawal,Deposit and Balance inquiry

-class Withdrawal extends Transaction and implements the functions for a withdrawal in the ATM

-class Deposit extends Transaction and implements the functions for a deposit in the ATM

-class BalanceInquiry extends Transaction and implements the functions for a balance inquiry in the ATM

-class ATM is the cheif class as all other classes are inconcated into it

-class AtmCaseStudy contains the main class that tests the ATM functionalities

THANK YOU!
