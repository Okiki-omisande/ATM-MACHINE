public class Withdrawal extends Transaction {

    private double Amount;
    private Keypad Keypad;
    private CashDispenser CashDispenser;

    private static final int CANCELLED = 6;

    public Withdrawal(int userAccountNumber, BankDataBase atmBankDataBase, Screen atmScreen, Keypad keypad, CashDispenser cashDispenser) {
        super(userAccountNumber, atmBankDataBase, atmScreen);
        Keypad = keypad;
        CashDispenser = cashDispenser;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double amount;

        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();


        do{
            amount = displayMenuAmount();
            double availableAmount;
            if (amount != CANCELLED) {
               availableAmount = bankDataBase.getAvailableBalance(getAccountNumber());
               if (amount <= availableAmount) {
                   if (CashDispenser.isAvailable(amount)) {
                       bankDataBase.debitAccount(getAccountNumber(),amount);
                       CashDispenser.dispenseCash(amount);
                       cashDispensed = true;
                       screen.displayMessageLine("Please take your cash now");
                   }
                   else
                       screen.displayMessageLine("""

                               Insufficient cash available in the atm
                               Please enter a smaller amount""");
               }
               else
                   screen.displayMessageLine("""

                           Insufficient funds in your account
                           Please enter a smaller amount""");
            }
            else
                screen.displayMessageLine("Cancelling Transaction");
        }
        while (!cashDispensed);

    }

    public int displayMenuAmount() {
        int userChoice = 0;
        Screen screen = getScreen();
        int[] Amount = {20, 40, 80, 100, 200, 6};

        while (userChoice == 0) {
            screen.displayMessageLine("Withdrawal Menu");
            screen.displayMessageLine("1- $20");
            screen.displayMessageLine("2- $40");
            screen.displayMessageLine("3- $80");
            screen.displayMessageLine("4- $100");
            screen.displayMessageLine("5- $200");
            screen.displayMessageLine("6- Cancel");
            int input = Keypad.getInput();

            switch (input) {
                case 1, 2, 3, 4, 5 -> userChoice = Amount[input-1];
                case 6 -> {
                    userChoice = CANCELLED;
                    screen.displayMessageLine("Transaction Cancelled..."+ "\n");
                }
                default -> screen.displayMessageLine("Invalid input. Please try again" + "\n");
            }
        }
        return userChoice;
    }
}
