import java.text.DecimalFormat;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final String[] subjects = { "English", "Tamil", "Maths", "Science", "Social Science" };
    private static final int PASS_MARK = 50;
    private static boolean flag = false;

    public static void main(String[] args) {
        try {
            int totalMarks = inputMarks();
            double averagePercentage = calculateAverage(totalMarks, subjects.length);
            char grade = calculateGrade(averagePercentage);
            displayResult(totalMarks, averagePercentage, grade);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static int inputMarks() {
        int totalMarks = 0;
        for (String subject : subjects) {
            totalMarks += inputMark(subject);
        }
        return totalMarks;
    }

    private static int inputMark(String subject) {
        int mark;
        do {
            System.out.print("Enter marks obtained in " + subject + " : ");
            mark = sc.nextInt();
            if (!checkValidMark(mark)) {
                System.out.println("Enter valid mark 0-100.");
            }
            if (mark < 50 && flag != true) {
                flag = true;
            }
        } while (!checkValidMark(mark));
        return mark;
    }

    private static double calculateAverage(int totalMarks, int subjectCount) {
        if (subjectCount == 0) {
            throw new IllegalArgumentException("Subjects cannot be empty.");
        }
        return totalMarks / (double) subjectCount;
    }

    private static String formatPercentage(double percentage) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(percentage) + "%";
    }

    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= PASS_MARK && flag == false) {
            return new GradeCalculation(averagePercentage).getGrade();
        } else {
            // Fail if the average percentage is below the pass mark in any subject, and
            // also if any individual subject's mark is below the pass mark, even if the
            // overall percentage is above the pass mark.
            return 'U';

        }
    }

    private static void displayResult(int totalMarks, double averagePercentage, char grade) {
        System.out.println("---------------------------------------------------");
        System.out.println("\tTotal mark\tPercentage\tGrade");
        System.out.println("---------------------------------------------------");
        System.out.println("\t" + totalMarks + "\t\t" + formatPercentage(averagePercentage) + "\t\t" + grade);
        System.out.println("---------------------------------------------------");
        System.out.println(
                "Note: Fail if the average percentage is below the pass mark in any subject, and also if any individual subject's mark is below the pass mark, even if the overall percentage is above the pass mark.\n");
    }

    private static boolean checkValidMark(int mark) {
        return mark >= 0 && mark <= 100;
    }
}
