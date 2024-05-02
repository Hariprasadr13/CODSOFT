public class GradeCalculation {
    double averageMark;

    public GradeCalculation(double averageMark) {
        this.averageMark = averageMark;
    }

    public char getGrade() {
        double mark = this.averageMark;
        if (mark >= 90) {
            return 'O';
        } else if (mark < 90 && mark >= 80) {
            return 'A';
        } else if (mark < 80 && mark >= 70) {
            return 'B';
        } else if (mark < 70 && mark >= 60) {
            return 'C';
        } else if (mark < 60 && mark >= 50) {
            return 'D';
        } else {
            return 'U';
        }
    }
}