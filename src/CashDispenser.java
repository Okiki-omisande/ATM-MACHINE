public class CashDispenser {
    private static final int INITIAL_COUNT = 500;
    int count;

    public CashDispenser() {
        count = INITIAL_COUNT;
    }

    public void dispenseCash(double Amount) {
        double cashRequired = Amount / 20;
        count -= cashRequired;
    }

    public boolean isAvailable(double Amount) {
        double cashRequired = Amount / 20;
        return cashRequired <= count;
    }
}
