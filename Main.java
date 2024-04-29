import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Official[] officials = new Official[5];
    public static Teacher[] teachers = new Teacher[10];

    public static Student[] students = new Student[20];

    private static int currentstudents = 0;
    private static int currentteachers = 0;
    private static int currentofficials = 0;
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
            fn= scanner.next();
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
        }while (fn.isEmpty() || ln.isEmpty() || username.isEmpty() || email.isEmpty() ||phonenumber.isEmpty() || pass.isEmpty() || repass.isEmpty() || !pass.equals(repass) || educationalID.isEmpty());

        System.out.println("To signup please chose your role(Teacher/Student/Official):");
        String userRole = scanner.next();
        switch (userRole) {
            case "Student" -> {
                String studyfield;
                do {
                    System.out.println("Please enter your studyfield :");
                    studyfield = scanner.next();
                } while (studyfield == null);
                // Check if user is repetitive
                boolean repeat = false;
                for (Student st : students) {
                    if (st != null && (st.getEducationalID().equals(educationalID) || st.getUsername().equals(username) || st.getEmail().equals(email) || st.getPhonenumber().equals(phonenumber))) {
                        System.out.println("this user is repetitive :");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    students[currentstudents++] = new Student(fn, ln, username, email, phonenumber, userRole, pass, studyfield, educationalID);
                    Main.printMenu();
                }

            }
            case "Teacher" -> {
                // Check if user is repetitive
                boolean repeat = false;
                for (Teacher te : teachers) {
                    if (te != null && (te.getUsername().equals(username) || te.getEmail().equals(email) || te.getPhonenumber().equals(phonenumber))) {
                        System.out.println("this user is repetitive :");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    teachers[currentteachers++] = new Teacher(fn, ln, username, email, phonenumber, userRole, pass, educationalID);
                    Main.printMenu();
                }

            }
            case "Official" -> {
                // Check if user is repetitive
                boolean repeat = false;
                for (Official ofi : officials) {
                    if (ofi != null && (ofi.getUsername().equals(username) || ofi.getEmail().equals(email) || ofi.getPhonenumber().equals(phonenumber))) {
                        System.out.println("this user is repetitive :");
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    officials[currentofficials++] = new Official(fn, ln, username, email, phonenumber, userRole, pass, educationalID);
                    Main.printMenu();
                }
            }
            default -> System.out.println("Wrong User!");
        }


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
                if (student.getPass().equals(userPass) && student.getEducationalID().equals(userId)) {
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
                if (teacher.getPass().equals(userPass) && teacher.getEducationalCode().equals(userCode)) {
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
