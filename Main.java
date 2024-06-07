import java.io.FileNotFoundException;
import java.io.IOException;
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

    private static void loadAllData() {
        try {
            teachers = FileManager.loadTeachers();
            students = FileManager.loadStudents();
            for (Teacher teacher : teachers) {
                teacher.units = FileManager.loadClasses(teacher,students);
                FileManager.loadNotifications(teacher.units);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data files not found, starting with empty lists.");
        }
    }

    private static void saveAllData() {
        try {
            FileManager.saveTeachers(teachers);
            FileManager.saveStudents(students);
            for (Teacher teacher : teachers) {
                FileManager.saveClasses(teacher.units);
                FileManager.saveNotifications(teacher.units);
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

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
        System.out.println("3 - Exit");
        System.out.println("Please enter your function number:");
        int function = scanner.nextInt();
        scanner.nextLine();
        if (function == 1) {
            login();
        } else if (function == 2) {
            signUp();
        } else if (function == 3) {
            saveAllData();
            System.exit(0);
        } else {
            System.out.println("Invalid input! Please enter 1 or 2 or 3");
            firstMenu();
        }
    }

    private static void signUp() {
        String fn, ln, username, email, phoneNumber, pass, repass, userRole, educationalID;
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
            System.out.println("Please enter your password :");
            pass = scanner.next();
            System.out.println("Please enter your password again :");
            repass = scanner.next();
            System.out.println("Please choose your role (Teacher/Student/Official):");
            userRole = scanner.next();
            System.out.println("Please enter your educational ID :");
            educationalID = scanner.next();
        } while (fn.isEmpty() || ln.isEmpty() || username.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || pass.isEmpty() || repass.isEmpty() || !pass.equals(repass) || educationalID.isEmpty() || userRole.isEmpty());

        if (isUserDuplicate(username, email, phoneNumber, educationalID)) {
            System.out.println("This user is repetitive.");
            return;
        }

        switch (userRole) {
            case "Student" -> {
                String studyField;
                do {
                    System.out.println("Please enter your study field :");
                    studyField = scanner.next();
                } while (studyField.isEmpty());
                students.add(new Student(fn, ln, username, email, phoneNumber, userRole, pass, educationalID, studyField));
                System.out.println("Student signed up successfully.");
            }
            case "Teacher" -> {
                teachers.add(new Teacher(fn, ln, username, email, phoneNumber, userRole, pass, educationalID));
                System.out.println("Teacher signed up successfully.");
            }
            case "Official" -> {
                officials.add(new Official(fn, ln, username, email, phoneNumber, userRole, pass, educationalID));
                System.out.println("Official signed up successfully.");
            }
            default -> System.out.println("Wrong User!");
        }
    }

    private static boolean isUserDuplicate(String username, String email, String phoneNumber, String educationalID) {
        for (Student student : students) {
            if (student.getEducationalID().equals(educationalID) || student.getUsername().equals(username) || student.getEmail().equals(email) || student.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(username) || teacher.getEmail().equals(email) || teacher.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        for (Official official : officials) {
            if (official.getUsername().equals(username) || official.getEmail().equals(email) || official.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private static void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        Person user = findUserByUsername(username);
        if (user != null && user.getPass().equals(password)) {
            System.out.println("Welcome, " + user.getFirstName() + "!");
            user.selectMenu();
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private static Person findUserByUsername(String username) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(username)) {
                return teacher;
            }
        }
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        loadAllData();
        for (int i = 0; i < 6; i++) {
            firstMenu();
        }
    }
}
