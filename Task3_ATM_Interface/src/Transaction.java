import java.util.Scanner;

class TransactionFactory {
    public static Transaction createTransaction() {
        return new Transaction();
    }
}

class Transaction {
    private TransactionOperation transactionOperation;

    public void setTransactionOperation(TransactionOperation transactionOperation) {
        this.transactionOperation = transactionOperation;
    }

    public boolean transationProcess(BankAccount account, Scanner sc) {
        App.printMenu();
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid option (1-4): ");
                sc.next();
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    setTransactionOperation(new Withdrawal());
                    break;
                case 2:
                    setTransactionOperation(new Deposit());
                    break;
                case 3:
                    setTransactionOperation(new CheckBalance());
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Enter valid option : ");
                    App.printMenu();
                    continue;
            }
            transactionOperation.execute(account, sc);
            if (!App.continueDecision(sc)) {
                return false;
            }
            App.printMenu();
        }
    }
}

interface TransactionOperation {
    void execute(BankAccount account, Scanner sc);
}

class Withdrawal implements TransactionOperation {
    public void execute(BankAccount account, Scanner sc) {
        System.out.print("Enter Amount you want to withdraw : ");
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid amount: ");
            sc.next();
        }
        double withdrawAmount = sc.nextDouble();
        double balance = account.getBalance();

        if (withdrawAmount > balance) {
            System.out.println("Insufficient Balance. Please try again.");
            execute(account, sc);
        } else if (!checkNotes(withdrawAmount)) {
            System.out.println("Please enter amount with multiples of 100, 200, or 500");
            execute(account, sc);
        } else {
            account.setBalance(balance - withdrawAmount);
            System.out.println("Take your cash and remaining balance is " + account.getBalance());
        }
    }

    private boolean checkNotes(double withdrawAmount) {
        return withdrawAmount % 100 == 0 || withdrawAmount % 200 == 0 || withdrawAmount % 500 == 0;
    }
}

class Deposit implements TransactionOperation {
    public void execute(BankAccount account, Scanner sc) {
        System.out.print("Enter Amount you want to Deposit : ");
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid amount: ");
            sc.next();
        }
        double depositAmount = sc.nextDouble();
        if (depositAmount > 0 && checkNotes(depositAmount)) {
            account.setBalance(account.getBalance() + depositAmount);
            System.out.println("Amount credited successfully and your balance is " + account.getBalance());
        } else {
            System.out.println("Please enter amount with multiples of 100, 200, or 500");
            execute(account, sc);
        }
    }

    private boolean checkNotes(double depositAmount) {
        return depositAmount % 100 == 0 || depositAmount % 200 == 0 || depositAmount % 500 == 0;
    }
}

class CheckBalance implements TransactionOperation {
    public void execute(BankAccount account, Scanner sc) {
        System.out.println("Your Account Balance is : " + account.getBalance());
    }
}