import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentManagementSystem {
    public static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void addStudent(Scanner sc) {
        while (true) {
            sc.nextLine();
            System.out.print("Enter Name : ");
            String sname = sc.nextLine().trim();
            System.out.print("Enter RollNo : ");
            String srollno = sc.nextLine().trim();
            System.out.print("Enter Grade : ");
            char sgrade = sc.next().charAt(0);

            if (sname.isEmpty() || srollno.isEmpty() || sgrade == ' ') {
                System.out.println("Error: Please fill in all required fields.");
            } else if (!isValidInput(sname, "^[a-zA-Z]+(?:[' -][a-zA-Z]+)*$")
                    || !isValidInput(srollno, "^[a-zA-Z0-9]+$")) {
                System.out.println("Please enter valid input");
            } else {
                try {
                    if (!studentDatabase.containsKey(srollno)) {
                        studentDatabase.put(srollno, new Student(sname, srollno, sgrade));
                    } else {
                        System.out.println("This student already exists...");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    public static void removeStudent(Scanner sc) {
        System.out.print("Enter RollNo : ");
        String rollno = sc.next();
        if (studentDatabase.containsKey(rollno)) {
            studentDatabase.remove(rollno);
            System.out.println("Removed Successfully");
        } else {
            System.out.println("Enter valid RollNo...");
        }
    }

    public static void updateStudent(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter RollNo : ");
        String rollno = sc.nextLine().trim();
        if (studentDatabase.containsKey(rollno)) {
            Student s = studentDatabase.get(rollno);
            boolean flag = true;
            while (flag) {
                System.out.println("Enter which attribute you want to update:");
                s.printAttributes();
                int n = sc.nextInt();
                sc.nextLine();
                switch (n) {
                    case 1:
                        System.out.print("Enter updated name : ");
                        s.setName(sc.nextLine().trim());
                        flag = App.continueDecision();
                        break;
                    case 2:
                        System.out.print("Enter updated rollno : ");
                        s.setRollNo(sc.nextLine().trim());
                        flag = App.continueDecision();
                        break;
                    case 3:
                        System.out.print("Enter updated grade : ");
                        s.setGrade(sc.next().charAt(0));
                        flag = App.continueDecision();
                        break;
                    default:
                        flag = false;
                        break;
                }
            }
            studentDatabase.replace(rollno, s);
        } else {
            System.out.println("Enter valid RollNo...");
        }
    }

    public static void searchStudent(Scanner sc) {
        System.out.print("Enter RollNo : ");
        String rollno = sc.next();
        if (studentDatabase.containsKey(rollno)) {
            Student s = studentDatabase.get(rollno);
            System.out.println(s.toString());
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void displayStudent() {
        if (!studentDatabase.isEmpty()) {
            print();
        } else {
            System.out.println("There is no data in the database. Please add some students.");
        }
    }

    public static void print() {
        System.out.println("----------------------------------------------");
        System.out.println("RollNo\tName\tGrade");
        System.out.println("----------------------------------------------");
        for (Map.Entry<String, Student> entry : studentDatabase.entrySet()) {
            Student s = entry.getValue();
            System.out.println(s.getRollNo() + "\t" + s.getName() + "\t" + s.getGrade());
        }
        System.out.println("----------------------------------------------");
    }

    public static boolean isValidInput(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
