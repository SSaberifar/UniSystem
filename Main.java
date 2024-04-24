import java.util.Scanner;
public class Main {
    public static void printMenu(){
        System.out.println("Teacher/Student classes:");
        System.out.println("Tasks(quiz,exam,exercise):");
        System.out.println("notifications:");
        System.out.println("user profile:");
        System.out.println(System.currentTimeMillis());
    }
    public static void setFunction(int number){
        if(number==1){
            LoginPanel.operate();
        }else if (number==2){
            SignUpPanel.operate();
        }else{
            System.out.println("Wrong Number!");
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Login");
        System.out.println("2 - SignUp");
        System.out.println("Please enter your function number:");
        int function = scanner.nextInt();
        setFunction(function);
    }
}
