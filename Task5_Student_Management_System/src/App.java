import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    StudentManagementSystem.addStudent(sc);
                    break;
                case 2:
                    StudentManagementSystem.removeStudent(sc);
                    break;
                case 3:
                    StudentManagementSystem.searchStudent(sc);
                    break;
                case 4:
                    StudentManagementSystem.updateStudent(sc);
                    break;
                case 5:
                    StudentManagementSystem.displayStudent();
                    break;
                case 6:
                    System.out.println("Thank you...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
            if (!continueDecision()) {
                System.out.println("Thank you...");
                return;
            }
        }
    }

    public static void printMenu() {
        System.out.println("***********Welcome to Student Management System***********");
        System.out.println(
                "1. Add Student\n2. Remove Student\n3. Search Student\n4. Update Student\n5. Display Students\n6. Exit Application");
        System.out.println("**********************************************************");
    }

    public static boolean continueDecision() {
        System.out.print("Do you want to continue... Press Y or N : ");
        char decision = sc.next().charAt(0);
        if (decision == 'N' || decision == 'n') {
            return false;
        } else if (decision == 'Y' || decision == 'y') {
            return true;
        } else {
            System.out.println("Enter valid input...");
            return continueDecision();
        }
    }

    public static int getIntInput(String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }
        return input;
    }
}
