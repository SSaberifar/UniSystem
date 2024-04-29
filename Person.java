import java.util.regex.Pattern;

public class Person {
    private String fisrtname;
    private String lastname;
    private String username;
    private String email;
    private String phonenumber;
    private String role;
    private String pass;

    public Person(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass) {
        this.setFisrtname(fisrtname);
        this.setLastname(lastname);
        this.setUsername(username);
        this.setEmail(email);
        this.setPhonenumber(phonenumber);
        this.setRole(role);
        this.setPass(pass);
    }

    public boolean isValidString(String input) {
        return Pattern.matches("^[a-zA-Z]{1,18}$", input);
    }

    public boolean isValidId(String id, String role) {
        if (role.equals("student")) {
            return Pattern.matches("^[0-9]{6}$", id);
        } else if (role.equals("teacher")) {
            return Pattern.matches("^[0-9]{10}$", id);
        } else if (role.equals("official")) {
            return Pattern.matches("^[0-9]{4}$", id);
        }
        return false;
    }


    public void setFisrtname(String fn) {
        if (isValidString(fn)) {
            this.fisrtname = fn;
        }else {
            System.out.println("First name is not valid");
        }
    }

    public void setLastname(String ln) {
        if (isValidString(ln)) {
            this.lastname = ln;
        } else {
            System.out.println("Last name is not valid");
        }
    }

    public void setUsername(String username) {
        if (Pattern.matches("^[A-Za-z0-9]{5,12}$", username)) {
            this.username = username;
        } else {
            System.out.println("Username is not valid ");
        }
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

    public void setEmail(String email) {
        if (Pattern.matches("^[a-zA-Z0-9._%+-]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}$", email)) {
            this.email = email;
        } else {
            System.out.println("email is not valid");
        }
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        if (Pattern.matches("^09\\d{9}$", phonenumber)) {
            this.phonenumber = phonenumber;
        } else {
            System.out.println("phone number is not valid");
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

    public void setPass(String pass) {
        if (Pattern.matches("^[A-Za-z0-9]{8,12}$", pass)) {
            this.pass = pass;
        } else {
            System.out.println("pass is not valid");
        }
    }

}
