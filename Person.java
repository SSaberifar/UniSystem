import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Person {

    Scanner scanner = new Scanner(System.in);
    List<Unit> units = new ArrayList<>(10);
    private String fisrtname;
    private String lastname;
    private String username;
    private String email;
    private String phonenumber;
    private String role;
    private String pass;


    public Person(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass) {
        try {
            this.setFisrtname(fisrtname);
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
            this.setPhonenumber(phonenumber);
        } catch (Exceptions.InvalidPhoneException e) {
            throw new RuntimeException(e);
        }
        try {
            this.setPass(pass);
        } catch (Exceptions.InvalidPassException e) {
            throw new RuntimeException(e);
        }
        this.setRole(role);
    }

    ////////////////////setter&getter/////////////////

    public String getFisrtname() {
        return fisrtname;
    }

    public void setFisrtname(String fn) throws Exceptions.InvalidFNException {
        if (isValidString(fn)) {
            this.fisrtname = fn;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) throws Exceptions.InvalidPhoneException {
        if (Pattern.matches("^09\\d{9}$", phonenumber)) {
            this.phonenumber = phonenumber;
        } else {
            throw new Exceptions.InvalidPhoneException("please enter valid phone number");
        }
    }

    public String getRole() {
        return role;
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

    public abstract void showclasses();

    protected abstract void showtasks();

    public abstract void shownotif();

    public void showInfo() {
        System.out.println(getFisrtname());
        System.out.println(getLastname());
        System.out.println(getEmail());
        System.out.println(getPhonenumber());
        System.out.println(getPass());
        System.out.println();
    }

    public void selectmenu() {
        Main.printMenu();
        System.out.println("Please enter your operation:");
        int ope = scanner.nextInt();
        switch (ope) {
            case 1:
                showclasses();
                selectmenu();
                break;
            case 2:
                showtasks();
                selectmenu();
                break;
            case 3:
                shownotif();
                selectmenu();
                break;
            case 4:
                showInfo();
                selectmenu();
                break;
            case 5:
                Main.firstmenu();
                break;
        }
    }

}
