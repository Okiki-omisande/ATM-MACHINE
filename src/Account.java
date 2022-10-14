public class Account {
    private int AccountNumber;
    private int Pin;
    private double TotalBalance;
    private double AvailableBalance;

    public Account(int TheAccountNUmber, int ThePin,int TheAvailableBalance, int TheTotalBalance) {
        AccountNumber = TheAccountNUmber;
        Pin = ThePin;
        AvailableBalance = TheAvailableBalance;
        TotalBalance = TheTotalBalance;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public boolean VerifyPin(int pin) {
        return Pin == pin;
    }

    public double getTotalBalance() {
        return TotalBalance;
    }

    public double getAvailableBalance() {
        return AvailableBalance;
    }

    public double credit(double amount) {
        return TotalBalance += amount;
    }

    public double debit(double amount) {
        TotalBalance -= amount;
        return AvailableBalance -= amount;
    }
}
