import java.util.HashMap;

class BankAccount {
    String name;
    int accountNumber;
    int pinNumber;
    double balance;

    public static HashMap<Integer, BankAccount> accounts = new HashMap<>();

    // Static bank accounts for transactions
    static {
        accounts.put(5802021, new BankAccount("Hariprasad", 5802021, 1313, 1000));
        accounts.put(5802022, new BankAccount("Subashri", 5802022, 1010, 1500));
    }

    public BankAccount(String name, int accountNumber, int pinNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
    }

    public static boolean checkValidAccount(Integer accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public static boolean checkValidPinNumber(Integer accountNumber, Integer pinNumber) {
        return accounts.get(accountNumber).pinNumber == pinNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }
}
