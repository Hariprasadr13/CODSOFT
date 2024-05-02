import java.text.DecimalFormat;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);
    static String subjects[] = { "English", "Tamil", "Maths", "Science", "Social Science" };

    public static void main(String[] args) throws Exception {
        try {
            int totalMarks = 0;
            int subjectCount = subjects.length;
            int i = 0;
            while (i < subjectCount) {
                System.out.print("Enter marks obtained in " + subjects[i] + " : ");
                int mark = sc.nextInt();
                if (checkValidMark(mark)) {
                    totalMarks += mark;
                    i++;
                } else {
                    // System.out.println("Enter valid mark....");
                }
            }
            double averagePercentage = calculateAverage(totalMarks, subjectCount);
            char grade = new GradeCalculation(averagePercentage).getGrade();
            displayResult(totalMarks, averagePercentage, grade);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static double calculateAverage(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Subjects cannot be empty.");
        }
        return (numerator / denominator);
    }

    public static String formatPercentage(double percentage) {
        DecimalFormat df = new DecimalFormat("0.00"); // Format to two decimal places
        return df.format(percentage) + "%";
    }

    public static void displayResult(int totalMarks, double averagePercentage, char grade) {
        System.out.println("---------------------------------------------------");
        System.out.println("\tTotal mark\tPercentage\tGrade");
        System.out.println("---------------------------------------------------");
        System.out.println("\t" + totalMarks + "\t\t" + formatPercentage(averagePercentage) + "\t\t" + grade);
        System.out.println("---------------------------------------------------");
    }

    public static boolean checkValidMark(int mark) {
        return ((mark >= 0) && (mark <= 100) ? true : false);
    }
}
