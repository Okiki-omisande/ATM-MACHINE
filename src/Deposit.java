public class Deposit extends Transaction {
    private static final int CANCEL = 0;
    double amount;
    Keypad keypad;
    DepositSlot depositSlot;
    public Deposit(int userAccountNumber, BankDataBase atmBankDataBase, Screen atmScreen, Keypad atmKeypad, DepositSlot atmDepositSlot) {
        super(userAccountNumber,atmBankDataBase,atmScreen);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }
    @Override
    public void execute() {
        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();
        amount = promptForDeposit();

        if (amount != CANCEL) {
            screen.displayMessage("Please enter a deposit envelope containing ");
            screen.displayAmount(amount);
            screen.displayMessageLine(".");

            boolean envelopeReceived = depositSlot.isEnvelopeReceived();
            if (envelopeReceived) {
                bankDataBase.creditAccount(getAccountNumber(),amount);
                screen.displayMessageLine("""
                        The envelope has been received
                        NOTE: the money wont be available for withdrawal until
                              we verify the amount of any enclosed cash or checks.""" + "\n");
            }
            else
                screen.displayMessageLine("Transaction cancelled as ATM received no deposit envelope." + "\n");
        }
        else
            screen.displayMessageLine("Cancelling Transaction..."+"\n");
    }

    public double promptForDeposit() {
        Screen screen = getScreen();
        screen.displayMessageLine(""" 
                Enter the deposit amount in cents OR
                Press 0 to cancel""");
        double input = keypad.getInput();
        if (input == CANCEL)
            return CANCEL;
        else
            return input / 100;
    }
}
