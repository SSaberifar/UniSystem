import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Official> officials = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        System.out.println("1- Teacher/Student classes");
        System.out.println("2- Tasks(quiz,exam,exercise)");
        System.out.println("3- notifications");
        System.out.println("4- user profile");
        System.out.println("5- back");
        System.out.println();
    }

    public static void firstmenu() {
        System.out.println("1 - Login");
        System.out.println("2 - SignUp");
        System.out.println("Please enter your function number:");
        int function = scanner.nextInt();
        setFunction(function);
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
        String fn;
        String ln;
        String username;
        String email;
        String phonenumber;
        String pass;
        String repass;
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
            System.out.println("Please enter your phonenumber :");
            phonenumber = scanner.next();
            System.out.println("Please enter your pass :");
            pass = scanner.next();
            System.out.println("Please enter your pass again :");
            repass = scanner.next();
            System.out.println("Please enter your educationalID :");
            educationalID = scanner.next();
        } while (fn.isEmpty() || ln.isEmpty() || username.isEmpty() || email.isEmpty() || phonenumber.isEmpty()
                || pass.isEmpty() || repass.isEmpty() || !pass.equals(repass) || educationalID.isEmpty());

        System.out.println("To signup please chose your role (Teacher/Student/Official):");
        String userRole = scanner.next();

        switch (userRole) {
            case "Student" -> {
                String studyfield;
                do {
                    System.out.println("Please enter your studyfield :");
                    studyfield = scanner.next();
                } while (studyfield.isEmpty());
                boolean repeat = false;
                for (Student student : students) {
                    if (student.getEducationalID().equals(educationalID)
                            || student.getUsername().equals(username)
                            || student.getEmail().equals(email)
                            || student.getPhonenumber().equals(phonenumber)) {
                        System.out.println("This user is repetitive.");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    students.add(new Student(fn, ln, username, email, phonenumber, userRole, pass, studyfield, educationalID));
                    System.out.println("Student signed up successfully.");
                }
            }
            case "Teacher" -> {
                boolean repeat = false;
                for (Teacher teacher : teachers) {
                    if (teacher.getUsername().equals(username)
                            || teacher.getEmail().equals(email)
                            || teacher.getPhonenumber().equals(phonenumber)) {
                        System.out.println("This user is repetitive.");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    teachers.add(new Teacher(fn, ln, username, email, phonenumber, userRole, pass, educationalID));
                    System.out.println("Teacher signed up successfully.");
                }
            }
            case "Official" -> {
                boolean repeat = false;
                for (Official official : officials) {
                    if (official.getUsername().equals(username)
                            || official.getEmail().equals(email)
                            || official.getPhonenumber().equals(phonenumber)) {
                        System.out.println("This user is repetitive.");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    officials.add(new Official(fn, ln, username, email, phonenumber, userRole, pass, educationalID));
                    System.out.println("Official signed up successfully.");
                }
            }
            default -> System.out.println("Wrong User!");
        }
    }

    private static void Login() {
        System.out.println("Please enter your role (Teacher/Student/Official):");
        String userRole = scanner.next();
        boolean loggedIn = false;

        switch (userRole) {
            case "Student" -> {
                System.out.println("Please Enter your education Id:");
                String userId = scanner.next();
                System.out.println("Please Enter your password:");
                String userPass = scanner.next();

                for (Student student : students) {
                    if (student.getPass().equals(userPass)
                            && student.getEducationalID().equals(userId)) {
                        System.out.println("Welcome " + student.getUsername());
                        student.selectmenu();
                        loggedIn = true;
                        break;
                    }
                }
            }
            case "Teacher" -> {
                System.out.println("Please Enter your education code:");
                String userCode = scanner.next();
                System.out.println("Please Enter your password:");
                String userPass = scanner.next();

                for (Teacher teacher : teachers) {
                    if (teacher.getPass().equals(userPass)
                            && teacher.getEducationalCode().equals(userCode)) {
                        System.out.println("Welcome " + teacher.getUsername());
                        teacher.selectmenu();
                        loggedIn = true;
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
                    if (official.getPass().equals(userPass)
                            && official.getEducationalCode().equals(userCode)) {
                        System.out.println("Welcome " + official.getUsername());
                        official.selectmenu();
                        loggedIn = true;
                        break;
                    }
                }
            }
            default -> System.out.println("Wrong User!");
        }

        if (!loggedIn) {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            firstmenu();
        }
    }
}
