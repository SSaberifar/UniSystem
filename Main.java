import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Official[] officials = new Official[5];
    public static Teacher[] teachers = new Teacher[10];

    public static Student[] students = new Student[20];

    public static void printMenu() {
        System.out.println("Teacher/Student classes:");
        System.out.println("Tasks(quiz,exam,exercise):");
        System.out.println("notifications:");
        System.out.println("user profile:");
        System.out.println(System.currentTimeMillis());
    }

    public static void setFunction(int number) {
        if (number == 1) {
            Login();
        } else if (number == 2) {
            SignUp();
        } else {
            System.out.println("Wrong Number!");
        }
    }

    private static void SignUp() {
        //add code here soroosh :)
        Main.printMenu();
    }

    private static void Login() {
        System.out.println("Please enter your role(Teacher/Student/Official):");
        String userRole = scanner.next();
        if (userRole.equals("Student")) {
            String userId = null;
            while (userId == null) {
                System.out.println("Please Enter your education Id :");
                userId = scanner.next();
            }
            String userPass = null;
            while (userPass == null) {
                System.out.println("Please Enter your password :");
                userPass = scanner.next();
            }
            for (Student student : students) {
                if (student.getPass() == userPass && student.getEducationalID() == userId) {
                    System.out.println("Welcome!!!");
                    Main.printMenu();
                    break;
                }
            }
            System.out.println("User not found!!!");
            Main.printMenu();
        } else if (userRole.equals("Teacher")) {
            String userCode = null;
            while (userCode == null) {
                System.out.println("Please Enter your education code :");
                userCode = scanner.next();
            }
            String userPass = null;
            while (userPass == null) {
                System.out.println("Please Enter your password :");
                userPass = scanner.next();
            }
            for (Teacher teacher : teachers) {
                if (teacher.getPass() == userPass && teacher.getEducationalCode() == userCode) {
                    System.out.println("Welcome!!!");
                    Main.printMenu();
                    break;
                }
            }
            System.out.println("User not found!!!");
        } else {
            System.out.println("Wrong User!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Login");
        System.out.println("2 - SignUp");
        System.out.println("Please enter your function number:");
        int function = scanner.nextInt();
        setFunction(function);
    }
}
