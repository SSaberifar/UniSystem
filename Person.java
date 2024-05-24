import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Person {

    Scanner scanner = new Scanner(System.in);
    List<Unit> units = new ArrayList<>(10);
    private String firstName;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;
    private String pass;
    private String educationalID;


    public Person(String firstName, String lastname, String username, String email, String phoneNumber, String role, String pass,String educationalID) {
        try {
            this.setFirstname(firstName);
        } catch (Exceptions.InvalidFNException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setLastname(lastname);
        } catch (Exceptions.InvalidLNException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setUsername(username);
        } catch (Exceptions.InvalidUNException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setEmail(email);
        } catch (Exceptions.InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setPhoneNumber(phoneNumber);
        } catch (Exceptions.InvalidPhoneException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setPass(pass);
        } catch (Exceptions.InvalidPassException e) {
            throw new RuntimeException(e);
        }
        setRole(role);
        try {
            this.setEducationalID(educationalID);
        } catch (Exceptions.InvalidIDException e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////setter&getter/////////////////

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String fn) throws Exceptions.InvalidFNException {
        if (isValidString(fn)) {
            this.firstName = fn;
        } else {
            throw new Exceptions.InvalidFNException("please enter valid first name");
        }
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String ln) throws Exceptions.InvalidLNException {
        if (isValidString(ln)) {
            this.lastname = ln;
        } else {
            throw new Exceptions.InvalidLNException("please enter valid last name");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exceptions.InvalidUNException {
        if (Pattern.matches("^[A-Za-z0-9]{5,12}$", username)) {
            this.username = username;
        } else {
            throw new Exceptions.InvalidUNException("please enter valid user name");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exceptions.InvalidEmailException {
        if (Pattern.matches("^[a-zA-Z0-9._%+-]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}$", email)) {
            this.email = email;
        } else {
            throw new Exceptions.InvalidEmailException("please enter valid email");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exceptions.InvalidPhoneException {
        if (Pattern.matches("^09\\d{9}$", phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new Exceptions.InvalidPhoneException("please enter valid phone number");
        }
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) throws Exceptions.InvalidPassException {
        if (Pattern.matches("^[A-Za-z0-9]{8,12}$", pass)) {
            this.pass = pass;
        } else {
            throw new Exceptions.InvalidPassException("please enter valid password");
        }
    }

    public String getEducationalID() {
        return educationalID;
    }

    public void setEducationalID(String educationalID) throws Exceptions.InvalidIDException {
        if (isValidId(educationalID, getRole())) {
            this.educationalID = educationalID;
            System.out.println("ID Processed");
        } else {
            throw new Exceptions.InvalidIDException("please enter valid student id");
        }
    }

    ////////////////////methods/////////////////

    public boolean isValidString(String input) {
        return Pattern.matches("^[a-zA-Z]{1,18}$", input);
    }

    public boolean isValidId(String id, String role) {
        return switch (role) {
            case "Student" -> Pattern.matches("^[0-9]{10}$", id);
            case "Teacher" -> Pattern.matches("^[0-9]{6}$", id);
            case "Official" -> Pattern.matches("^[0-9]{4}$", id);
            default -> false;
        };
    }

    public abstract void showClasses();

    protected abstract void showTasks();

    public abstract void showNotification();

    public void showInfo() {
        System.out.println(getFirstName());
        System.out.println(getLastname());
        System.out.println(getEmail());
        System.out.println(getPhoneNumber());
        System.out.println(getPass());
        System.out.println();
    }

    public void selectMenu() {
        Main.printMenu();
        System.out.println("Please enter your operation number:");
        int ope = scanner.nextInt();
        switch (ope) {
            case 1:
                showClasses();
                selectMenu();
                break;
            case 2:
                showTasks();
                selectMenu();
                break;
            case 3:
                showNotification();
                selectMenu();
                break;
            case 4:
                showInfo();
                selectMenu();
                break;
            case 5:
                Main.firstMenu();
                break;
        }
    }

}
