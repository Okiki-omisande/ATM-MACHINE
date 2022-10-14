public class BankDataBase {

    private Account[] accounts = new Account[2];

    public BankDataBase() {
        accounts[0] = new Account(12345,66688,5000,6800);
        accounts[1] = new Account(54321,77711,7000,8000);
    }

    private Account validateAccount(int accountNumber) {
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccountNumber() == accountNumber)
                return currentAccount;
        }
        return null;
    }
    public boolean authenticateUser(int AccountNumber, int Pin) {
        Account userAccount = validateAccount(AccountNumber);
        if (userAccount != null){
            return userAccount.VerifyPin(Pin);
        }
        else
            return false;
    }

    public double getAvailableBalance(int userAccountNumber) {
        return  validateAccount(userAccountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber) {
        return validateAccount(userAccountNumber).getTotalBalance();
    }

    public double creditAccount(int userAccountNumber, double amount) {
        return validateAccount(userAccountNumber).credit(amount);
    }

    public double debitAccount(int userAccountNumber, double amount) {
        return validateAccount(userAccountNumber).debit(amount);
    }
}
