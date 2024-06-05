import java.io.*;
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

    private static final String FILE_PATH = "users.txt";

    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                switch (parts[7]) {
                    case "Student":
                        students.add(new Student(parts[3], parts[4], parts[2], parts[5], parts[6], parts[7], parts[1], parts[0], parts[8]));
                        break;
                    case "Teacher":
                        teachers.add(new Teacher(parts[3], parts[4], parts[2], parts[5], parts[6], parts[7], parts[1], parts[0]));
                        break;
                    case "Official":
                        officials.add(new Official(parts[3], parts[4], parts[2], parts[5], parts[6], parts[7], parts[1], parts[0]));
                        break;
                    default:
                        System.out.println("Unknown role found in file: " + parts[7]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading users.");
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
        System.out.println("Please enter your function number:");
        int function = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (function == 1) {
            login();
        } else if (function == 2) {
            signUp();
        } else {
            System.out.println("Invalid input! Please enter 1 or 2.");
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
                saveUserToFile(new String[]{educationalID, pass, username, fn, ln, email, phoneNumber, userRole, studyField});
                System.out.println("Student signed up successfully.");
            }
            case "Teacher" -> {
                teachers.add(new Teacher(fn, ln, username, email, phoneNumber, userRole, pass, educationalID));
                saveUserToFile(new String[]{educationalID, pass, username, fn, ln, email, phoneNumber, userRole});
                System.out.println("Teacher signed up successfully.");
            }
            case "Official" -> {
                officials.add(new Official(fn, ln, username, email, phoneNumber, userRole, pass, educationalID));
                saveUserToFile(new String[]{educationalID, pass, username, fn, ln, email, phoneNumber, userRole});
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

    private static void saveUserToFile(String[] userInfo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            out.println(String.join(",", userInfo));
        } catch (IOException e) {
            System.out.println("Error in adding user to txt database");
        }
    }

    private static void login() {
        System.out.println("Please enter your role (Teacher/Student/Official):");
        String userRole = scanner.next();

        System.out.println("Please enter your educational ID:");
        String userId = scanner.next();
        System.out.println("Please enter your password:");
        String userPass = scanner.next();

        boolean isValid = validateLogin(userRole, userId, userPass);

        if (isValid) {
            System.out.println("Login successful!");
            switch (userRole) {
                case "Student" -> students.stream()
                        .filter(student -> student.getEducationalID().equals(userId))
                        .findFirst()
                        .ifPresent(Student::selectMenu);
                case "Teacher" -> teachers.stream()
                        .filter(teacher -> teacher.getEducationalID().equals(userId))
                        .findFirst()
                        .ifPresent(Teacher::selectMenu);
                case "Official" -> officials.stream()
                        .filter(official -> official.getEducationalID().equals(userId))
                        .findFirst()
                        .ifPresent(Official::selectMenu);
            }
        } else {
            System.out.println("Invalid ID or password.");
        }
    }

    private static boolean validateLogin(String userRole, String userId, String userPass) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (userId.equals(parts[0]) && userPass.equals(parts[1]) && userRole.equals(parts[7])) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error in finding user");
        }
        return false;
    }

    public static void main(String[] args) {
        loadUsers();
        for (int i = 0; i < 6; i++) {
            firstMenu();
        }
    }
}
