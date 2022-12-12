public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private BankDataBase bankDataBase;
    private static Screen screen;
    private CashDispenser cashDispenser;

    private static final int Balance_Inquiry = 1;
    private static final int Withdrawal = 2;
    private static final int Deposit = 3;
    private static final int Exit = 4;

    public ATM() {
        userAuthenticated = false;
        currentAccountNumber = 0;
        depositSlot = new DepositSlot();
        keypad = new Keypad();
        screen = new Screen();
        cashDispenser = new CashDispenser();
        bankDataBase = new BankDataBase();
    }

    public void run() {
        while (true) {
            while (!userAuthenticated) {
                screen.displayMessageLine("Welcome");
                authenticateUser();
            }
            performTransaction();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine("Thank you" + "\n");
        }
    }

    private void authenticateUser() {
        screen.displayMessageLine("Enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessageLine("Enter your pin: ");
        int pin = keypad.getInput();

        userAuthenticated = bankDataBase.authenticateUser(accountNumber,pin);

        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        }
        else
            screen.displayMessageLine("Invalid account number or pin. Please try again" + "\n");
    }

    private void performTransaction() {
        Transaction currentTransaction = null;
        boolean userExited = false;
        while (!userExited) {
            int mainMenuInput = displayMenu();

            switch (mainMenuInput) {
                case Balance_Inquiry:
                case Withdrawal:
                case Deposit:
                    currentTransaction = createTransaction(mainMenuInput);
                    currentTransaction.execute();
                    break;

                case Exit:
                    screen.displayMessageLine("System is exiting..." + "\n");
                    userExited = true;
                    break;

                default:
                    screen.displayMessageLine("Invalid input. Please enter a valid input" + "\n");

            }
        }
    }

    private int displayMenu() {
        screen.displayMessageLine("\n"+"Enter");
        screen.displayMessageLine("1- Balance Inquiry");
        screen.displayMessageLine("2- Withdrawal");
        screen.displayMessageLine("3- Deposit");
        screen.displayMessageLine("4- Exit");
        return keypad.getInput();
    }

    private Transaction createTransaction(int Type) {
        Transaction temp = null;
        switch (Type) {
            case Balance_Inquiry:
                temp = new BalanceInquiry(currentAccountNumber,bankDataBase,screen);
                break;

            case Withdrawal:
                temp = new Withdrawal(currentAccountNumber,bankDataBase,screen,keypad,cashDispenser);
                break;

            case Deposit:
                temp = new Deposit(currentAccountNumber,bankDataBase,screen,keypad,depositSlot);
                break;
        }
        return temp;
    }
}
