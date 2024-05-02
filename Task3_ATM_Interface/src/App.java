import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("---------Welcome to ATM Machine---------\n");
                System.out.print("Enter Your Account Number: ");
                Integer accountNumber = sc.nextInt();
                System.out.println();
                if (BankAccount.checkValidAccount(accountNumber)) {
                    System.out.print("Enter Pin Number: ");
                    int pinNumber = sc.nextInt();
                    System.out.println();
                    if (BankAccount.checkValidPinNumber(accountNumber, pinNumber)) {
                        BankAccount account = BankAccount.accounts.get(accountNumber);
                        System.out.println("Welcome " + account.name + "!");
                        Transaction transaction = TransactionFactory.createTransaction();
                        if (!transaction.transationProcess(account, sc)) {
                            System.out.println("Thank you..");
                            flag = false;
                        }
                    } else {
                        System.out.println("Your PIN number is incorrect... Please enter valid PIN number");
                    }
                } else {
                    System.out.println("Invalid account number.... Please enter valid account number");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sc.close();
        }
    }

    public static void printMenu() {
        System.out.print(
                "--------------------------------------\n\tTransaction Menu\n--------------------------------------\n");
        System.out.print(
                "1.Withdraw\n2.Deposit\n3.Check Balance\n4.Exit\n--------------------------------------\n\nWhat do you want to perform : ");
    }

    public static boolean continueDecision(Scanner sc) {
        System.out.print("Do you want to continue... Press Y or N : ");
        char decision = sc.next().charAt(0);
        if (decision == 'N' || decision == 'n') {
            return false;
        } else if (decision == 'Y' || decision == 'y') {
            return true;
        } else {
            System.out.println("Enter valid input...");
            return continueDecision(sc);
        }
    }
}
