import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.Scanner;

public class Teacher extends Person {

    Scanner scanner = new Scanner(System.in);

    public Teacher(String firstName, String lastname, String username, String email, String phoneNumber, String role, String pass, String educationalID) {
        super(firstName, lastname, username, email, phoneNumber, role, pass, educationalID);
    }


    ////////////////////methods/////////////////

    public void AddQuiz() {
        System.out.println("Enter unit name");
        String unitName = scanner.next();
        scanner.nextLine(); // Consume the remaining newline
        boolean validUnit = false;
        int unitIndex = 0;
        for (Unit unit : units) {
            if (unitName.equals(unit.getUnitname())) {
                validUnit = true;
                break;
            }
            unitIndex++;
        }
        if (validUnit) {
            System.out.println("Enter start date, quiz time, finish date and quiz name:");
            String startDate = scanner.next();
            String quizTime = scanner.next();
            String finishDate = scanner.next();
            String quizName = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            units.get(unitIndex).AddQuiz(startDate, quizTime, finishDate, quizName);
            Main.printMenu();
        } else {
            System.out.println("invalid unit name! do you want try again?y/n");
            if (scanner.next().charAt(0) == 'y') {
                scanner.nextLine(); // Consume the remaining newline
                AddQuiz();
            } else {
                System.out.println("Please enter your function number:");
                Main.printMenu();
            }
        }
    }

    public void AddUnit() throws Exceptions.CustomArrayException {
        if (units.size() >= 10) {
            throw new Exceptions.CustomArrayException("Cannot add more units, the array is full!");
        } else {
            System.out.println("Enter unit name:");
            String unitName = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            units.add(new Unit(unitName, this));
            System.out.println("Unit Created!");
        }
    }

    public void deleteUnit() {
        try {
            System.out.println("Enter unit name to delete:");
            String deleteUnit = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            boolean unitFound = false;
            for (Unit unit : units) {
                if (unit.getUnitname().equals(deleteUnit)) {
                    units.remove(unit);
                    System.out.println("Unit Removed!");
                    unitFound = true;
                    break;
                }
            }
            if (!unitFound) {
                throw new Exceptions.CustomArrayException("Unit not found!");
            }
        } catch (Exceptions.CustomArrayException e) {
            System.out.println("Array index out of bounds: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void AddStudent(String unitName, String username) {
        for (Student student : Main.students) {
            if (student.getUsername().equals(username)) {
                for (Unit unit : units) {
                    if (unit.getUnitname().equals(unitName)) {
                        unit.setStudents(student);
                        student.units.add(unit);
                        System.out.println("Student " + username + " Added!");
                        break;
                    }
                }
            }
        }
    }

    public void deleteStudent(String unitName, String username) {
        for (Student student : Main.students) {
            if (student.getUsername().equals(username)) {
                for (Unit unit : units) {
                    if (unit.getUnitname().equals(unitName)) {
                        unit.students.remove(student);
                        student.units.remove(unit);
                        System.out.println("Student " + username + " Removed!");
                        break;
                    }
                }
            }
        }
    }

    public void AddNotification(Unit unit) {
        System.out.println("Enter notification:");
        String notification = scanner.nextLine();
        unit.notifications.add(notification);
        System.out.println("Notification Added!");
    }

    public void deleteNotification(Unit unit) {
        System.out.println("Enter notification to delete:");
        String tag = scanner.nextLine();
        for (String sms : unit.notifications) {
            if (sms.equals(tag)) {
                unit.notifications.remove(sms);
                System.out.println("Notification Removed!");
                break;
            }
        }
    }

    @Override
    public void showClasses() {
        if (units.isEmpty()) {
            System.out.println("You don't have any classes!");
            System.out.println("Do you want to create a new class? (y/n)");
            String classOperation = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            if (classOperation.equals("y")) {
                try {
                    AddUnit();
                } catch (Exceptions.CustomArrayException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i) != null) {
                    System.out.println((i + 1) + "- " + units.get(i).unitname);
                }
            }
            System.out.println("Do you want to create a new class? (y/n)");
            String answer = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            if (answer.equals("y")) {
                try {
                    AddUnit();
                } catch (Exceptions.CustomArrayException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            System.out.println("Do you want to delete a class? (y/n)");
            String deleteOperation = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            if (deleteOperation.equals("y")) {
                deleteUnit();
            }
            System.out.println("Do you want to add a student? (y/n)");
            String addOperation = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            if (addOperation.equals("y")) {
                System.out.println("Enter unit name:");
                String addUnit = scanner.next();
                System.out.println("Enter student username:");
                String addStudent = scanner.next();
                scanner.nextLine(); // Consume the remaining newline
                AddStudent(addUnit, addStudent);
            }
            System.out.println("Do you want to delete a student? (y/n)");
            String deleteStudent = scanner.next();
            scanner.nextLine(); // Consume the remaining newline
            if (deleteStudent.equals("y")) {
                System.out.println("Enter unit name:");
                String unitName = scanner.next();
                System.out.println("Enter student username:");
                String studentName = scanner.next();
                scanner.nextLine(); // Consume the remaining newline
                deleteStudent(unitName, studentName);
            }
        }
        selectMenu();
    }

    @Override
    protected void showTasks() {
        if (units.isEmpty()){
            System.out.println("You dont have any unit");
            selectMenu();
        } else {

        }
        for ( Unit unit : units){

            System.out.println("unit "+unit.getName()+" tasks :");
            for (Quiz quiz : unit.getQuizzes()){
                if (quiz != null){
                    System.out.println("Quiz name : "+quiz.getName()+" deadline : "+quiz.getFdate());
                }
            }
            System.out.println("---------------------------");
            for ( Question question : unit.getQuestions() ){
                if ( question != null ) {
                    System.out.println("problem text : "+question.getQuestiontext()+", answer text : "+question.getAnswertext()+ "\t deadline : "+question.getFdate());
                    if ( !unit.getQuestionState(question.getFdate()) ) {
                        System.out.println("Students answers are ready!");
                    }
                }
            }
            System.out.println("---------------------------");
        }
    }

    @Override
    public void showNotification() {
        for (Unit unit : units) {
            if (unit.notifications.isEmpty()) {
                System.out.println("You don't have any notifications!");
                System.out.println("Do you want to create a new notification? (y/n)");
                String addNotification = scanner.next();
                scanner.nextLine(); // Consume the remaining newline
                if (addNotification.equals("y")) {
                    AddNotification(unit);
                }
            } else {
                for (int i = 0; i < unit.notifications.size(); i++) {
                    if (unit.notifications.get(i) != null) {
                        System.out.println(unit.notifications.get(i));
                    }
                }
                System.out.println("Do you want to delete a notification? (y/n)");
                String deleteNotification = scanner.next();
                scanner.nextLine(); // Consume the remaining newline
                if (deleteNotification.equals("y")) {
                    deleteNotification(unit);
                }
            }
        }
        selectMenu();
    }

    public void Correcting(String unitname ) {

        for (Unit unit : units){
            if (unit.getName().equals(unitname)){

            }
        }
        System.out.println("Correct students answers :");
        if ()
        do {

        }
    }
}
