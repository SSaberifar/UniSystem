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
            System.out.println("Enter start date , quiz time , finish date and quiz name :");
            units.get(unitIndex).AddQuiz(scanner.next(), scanner.next(), scanner.next(), scanner.next());
            Main.firstMenu();
        } else {
            System.out.println("invalid unit name! do you want try again?y/n");
            if (scanner.next().charAt(0) == 'y') {
                AddQuiz();
            } else {
                System.out.println("Please enter your function number:");
                Main.firstMenu();
            }
        }
    }

    public void AddUnit() throws Exceptions.CustomArrayException {
        if (units.size() >= 10) {
            throw new Exceptions.CustomArrayException("Cannot add more units, the array is full!");
        } else {
            String unitName = scanner.next();
            units.add(new Unit(unitName, this));
            System.out.println("Unit Created!");
        }
    }

    public void deleteUnit() {
        try {
            String deleteUnit = scanner.next();
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
        String notification = scanner.nextLine();
        unit.notifications.add(notification);
        System.out.println("notification Added!");
    }

    public void deleteNotification(Unit unit) {
        String tag = scanner.nextLine();
        for (String sms : unit.notifications) {
            if (sms.equals(tag)) {
                unit.notifications.remove(sms);
                System.out.println("notification Removed!");
                break;
            }
        }
    }

    @Override
    public void showClasses() {
        if (units.isEmpty()) {
            System.out.println("You dont have class!");
            System.out.println("Do you want create new class?(y/n)");
            String classOperation = scanner.next();
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
            System.out.println("Do you want create new class?(y/n)");
            String answer = scanner.next();
            if (answer.equals("y")) {
                try {
                    AddUnit();
                } catch (Exceptions.CustomArrayException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            System.out.println("Do you want delete new class?(y/n)");
            String deleteOperation = scanner.next();
            if (deleteOperation.equals("y")) {
                deleteUnit();
            }
            System.out.println("Do you want to add student:(y/n)");
            String addOperation = scanner.next();
            if (addOperation.equals("y")) {
                String addUnit = scanner.next();
                String addStudent = scanner.next();
                AddStudent(addUnit, addStudent);
            }
            System.out.println("Do you want to delete student:(y/n)");
            String deleteStudent = scanner.next();
            if (deleteStudent.equals("y")) {
                String unitName = scanner.next();
                String studentName = scanner.next();
                deleteStudent(unitName, studentName);
            }
        }
        selectMenu();
    }

    @Override
    protected void showTasks() {

    }

    @Override
    public void showNotification() {
        for (Unit unit : units) {
            if (unit.notifications.isEmpty()) {
                System.out.println("You dont have notification!");
                System.out.println("Do you want create new notification?(y/n)");
                String addNotification = scanner.next();
                if (addNotification.equals("y")) {
                    AddNotification(unit);
                }
            } else {
                for (int i = 0; i < unit.notifications.size(); i++) {
                    if (unit.notifications.get(i) != null) {
                        System.out.println(unit.notifications.get(i));
                    }
                }
                System.out.println("Do you want delete notification?(y/n)");
                String deleteNotification = scanner.next();
                if (deleteNotification.equals("y")) {
                    deleteNotification(unit);
                }
            }
        }
        selectMenu();
    }
}
