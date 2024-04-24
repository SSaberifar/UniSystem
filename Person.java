import java.util.Scanner;
import java.util.regex.Pattern;

public class Person {

    Scanner input= new Scanner(System.in);

    // Attribute
    protected String fisrtname;
    protected String lastname;
    protected String username;
    protected String email;
    protected String phonenumber;
    protected Person owner;
    protected String pass;
    protected String repass;

    // Constructor

    // Methods
    protected boolean isvalidstring( String input) {
        return Pattern.matches("^[a-zA-Z]+$",input);
    }
    protected boolean isvalidpass( String pass) {
        return Pattern.matches("^[a-zA-Z0-9]+$",pass);
    }

    // Setter And Getter
    public void setFisrtname(String fn) {
        if ( fn.length() <= 18 && isvalidstring( fn )) {
            this.fisrtname = fn;
        } else {
            System.out.println("First name not valid");
            setFisrtname( input.next() );
        }
    }

    public void setLastname(String ln) {
        if ( ln.length() <= 18 && isvalidstring( ln )) {
            this.lastname = ln;
        } else {
            System.out.println("Last name not valid");
            setLastname( input.next() );
        }
    }

    public void setUsername( String username) {
        if ( !username.isEmpty() && isvalidpass( username)) {
            this.username = username;
        } else {
            System.out.println("Username not valid ");
            setUsername( input.next() );
        }
    }

}
