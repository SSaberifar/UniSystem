import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Scanner;

public abstract class Person {

    Scanner scanner = new Scanner(System.in);
    private String fisrtname;
    private String lastname;
    private String username;
    private String email;
    private String phonenumber;
    private String role;
    private String pass;
    List<Unit> units = new ArrayList<>(10);


    public Person(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass) {
        this.setFisrtname(fisrtname);
        this.setLastname(lastname);
        this.setUsername(username);
        this.setEmail(email);
        this.setPhonenumber(phonenumber);
        this.setRole(role);
        this.setPass(pass);
    }

    ////////////////////setter&getter/////////////////

    public void setFisrtname(String fn) throws InvalidFNException {
        if (isValidString(fn)) {
            this.fisrtname = fn;
        } else {
            throw new InvalidFNException("please enter valid first name");
        }
    }

    public void setLastname(String ln) throws InvalidLNException {
        if (isValidString(ln)) {
            this.lastname = ln;
        } else {
            throw new InvalidLNException("please enter valid last name");
        }
    }

    public void setUsername(String username) throws InvalidUNException {
        if (Pattern.matches("^[A-Za-z0-9]{5,12}$", username)) {
            this.username = username;
        } else {
            throw new InvalidUNException("please enter valid user name");
        }
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (Pattern.matches("^[a-zA-Z0-9._%+-]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}$", email)) {
            this.email = email;
        } else {
            throw new InvalidEmailException("please enter valid email");
        }
    }

    public void setPhonenumber(String phonenumber) throws InvalidPhoneException {
        if (Pattern.matches("^09\\d{9}$", phonenumber)) {
            this.phonenumber = phonenumber;
        } else {
            throw new InvalidPhoneException("please enter valid phone number");
        }
    }

    public void setPass(String pass) throws InvalidPassException {
        if (Pattern.matches("^[A-Za-z0-9]{8,12}$", pass)) {
            this.pass = pass;
        } else {
            throw new InvalidPassException("please enter valid password");
        }
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFisrtname() {
        return fisrtname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public String getRole() {
        return role;
    }

    public String getPass() {
        return pass;
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
