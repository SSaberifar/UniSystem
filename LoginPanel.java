import java.util.Scanner;
public class LoginPanel {
    static Scanner scanner = new Scanner(System.in);
    public static void operate(){
        System.out.println("Please enter your role(Teacher/Student):");
        String userRole = scanner.next();
        if(userRole.equals("Student")){
            Main.printMenu();
        }else if(userRole.equals("Teacher")){
            Main.printMenu();
        }else{
            System.out.println("Wrong User!");
        }
    }
}
