public class Student {
    String name;
    String rollno;
    char grade;

    public Student(String name, String rollno, char grade) {
        this.name = name;
        this.rollno = rollno;
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(String rollno) {
        this.rollno = rollno;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollno;
    }

    public char getGrade() {
        return grade;
    }

    public void printAttributes() {
        System.out.println("1. Name");
        System.out.println("2. RollNo");
        System.out.println("3. Grade");
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nRollNo: " + rollno + "\nGrade: " + grade;
    }
}

