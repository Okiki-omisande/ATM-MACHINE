public abstract class Transaction {
    private int AccountNumber;
    private BankDataBase bankDataBase;
    private Screen screen;

    public Transaction(int userAccountNumber, BankDataBase atmBankDataBase, Screen atmScreen) {
        AccountNumber = userAccountNumber;
        bankDataBase = atmBankDataBase;
        screen = atmScreen;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public Screen getScreen() {
        return screen;
    }

    public BankDataBase getBankDataBase() {
        return bankDataBase;
    }

    abstract public void execute();
}
