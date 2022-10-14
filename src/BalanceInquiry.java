public class BalanceInquiry extends Transaction{
    public BalanceInquiry(int userAccountNumber, BankDataBase bankDataBase, Screen screen) {
        super(userAccountNumber,bankDataBase,screen);
    }

    @Override
    public void execute() {
        BankDataBase bankDataBase =  getBankDataBase();
        Screen screen = getScreen();

        double TotalBalance = bankDataBase.getTotalBalance(getAccountNumber());
        double AvailableBalance = bankDataBase.getAvailableBalance(getAccountNumber());

        screen.displayMessageLine("Balance Information");
        screen.displayMessage("Available Balance: ");
        screen.displayAmount(AvailableBalance);
        screen.displayMessage("Total Balance: ");
        screen.displayAmount(TotalBalance);
    }
}
