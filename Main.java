import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Official> officials = new ArrayList<>(3);
    public static List<Teacher> teachers = new ArrayList<>(5);
    public static List<Student> students = new ArrayList<>(10);
    static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        System.out.println("1- Teacher/Student classes");
        System.out.println("2- Tasks(quiz,exercise)");
        System.out.println("3- notifications");
        System.out.println("4- user profile");
        System.out.println("5- back");
        System.out.println();
    }

    public static void firstMenu() {
        System.out.println("1 - Login");
        System.out.println("2 - SignUp");
        System.out.println("Please enter your function number:");
        int function = scanner.nextInt();
        if (function == 1) {
            Login();
        } else if (function == 2) {
            SignUp();
        } else {
            System.out.println("Invalid input! Please enter 1 or 2.");
            firstMenu();
        }
    }

    private static void SignUp() {
        String fn;
        String ln;
        String username;
        String email;
        String phoneNumber;
        String pass;
        String repass;
        String userRole;
        String educationalID;
        do {
            System.out.println("Please enter your firstname :");
            fn = scanner.next();
            System.out.println("Please enter your lastname :");
            ln = scanner.next();
            System.out.println("Please enter your username :");
            username = scanner.next();
            System.out.println("Please enter your email :");
            email = scanner.next();
            System.out.println("Please enter your phone number :");
            phoneNumber = scanner.next();
            System.out.println("Please enter your pass :");
            pass = scanner.next();
            System.out.println("Please enter your pass again :");
            repass = scanner.next();
            System.out.println("Please choose your role (Teacher/Student/Official):");
            userRole = scanner.next();
            System.out.println("Please enter your educationalID :");
            educationalID = scanner.next();
        } while (fn.isEmpty() || ln.isEmpty() || username.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || pass.isEmpty() || repass.isEmpty() || !pass.equals(repass) || educationalID.isEmpty() || userRole.isEmpty());

        switch (userRole) {
            case "Student" -> {
                String studyField;
                do {
                    System.out.println("Please enter your study field :");
                    studyField = scanner.next();
                } while (studyField.isEmpty());
                boolean repeat = false;
                for (Student student : students) {
                    if (student.getEducationalID().equals(educationalID) || student.getUsername().equals(username) || student.getEmail().equals(email) || student.getPhoneNumber().equals(phoneNumber)) {
                        System.out.println("This user is repetitive.");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    students.add(new Student(fn, ln, username, email, phoneNumber, userRole, pass, educationalID, studyField));
                    System.out.println("Student signed up successfully.");

                }
            }
            case "Teacher" -> {
                boolean repeat = false;
                for (Teacher teacher : teachers) {
                    if (teacher.getUsername().equals(username) || teacher.getEmail().equals(email) || teacher.getPhoneNumber().equals(phoneNumber)) {
                        System.out.println("This user is repetitive.");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    teachers.add(new Teacher(fn, ln, username, email, phoneNumber, userRole, pass, educationalID));
                    System.out.println("Teacher signed up successfully.");
                }
            }
            case "Official" -> {
                boolean repeat = false;
                for (Official official : officials) {
                    if (official.getUsername().equals(username) || official.getEmail().equals(email) || official.getPhoneNumber().equals(phoneNumber)) {
                        System.out.println("This user is repetitive.");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    officials.add(new Official(fn, ln, username, email, phoneNumber, userRole, pass, educationalID));
                    System.out.println("Official signed up successfully.");
                }
            }
            default -> System.out.println("Wrong User!");
        }
    }

    private static void Login() {
        System.out.println("Please enter your role (Teacher/Student/Official):");
        String userRole = scanner.next();

        switch (userRole) {
            case "Student" -> {
                System.out.println("Please Enter your education ID:");
                String userId = scanner.next();
                System.out.println("Please Enter your password:");
                String userPass = scanner.next();

                for (Student student : students) {
                    if (student.getPass().equals(userPass) && student.getEducationalID().equals(userId)) {
                        System.out.println("Welcome " + student.getUsername());
                        student.selectMenu();
                        break;
                    }
                }
            }
            case "Teacher" -> {
                System.out.println("Please Enter your education ID:");
                String userCode = scanner.next();
                System.out.println("Please Enter your password:");
                String userPass = scanner.next();

                for (Teacher teacher : teachers) {
                    if (teacher.getPass().equals(userPass) && teacher.getEducationalID().equals(userCode)) {
                        System.out.println("Welcome " + teacher.getUsername());
                        teacher.selectMenu();
                        break;
                    }
                }
            }
            case "Official" -> {
                System.out.println("Please Enter your educational code:");
                String userCode = scanner.next();
                System.out.println("Please Enter your password:");
                String userPass = scanner.next();

                for (Official official : officials) {
                    if (official.getPass().equals(userPass) && official.getEducationalID().equals(userCode)) {
                        System.out.println("Welcome " + official.getUsername());
                        official.selectMenu();
                        break;
                    }
                }
            }
            default -> System.out.println("Wrong User!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            firstMenu();
        }
    }
}
