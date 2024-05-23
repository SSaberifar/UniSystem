import java.util.Scanner;

public class Teacher extends Person {

    Scanner scanner = new Scanner(System.in);
    private String educationalCode;

    public Teacher(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String educationalCode) {
        super(fisrtname, lastname, username, email, phonenumber, role, pass);
        try {
            this.setEducationalCode(educationalCode);
        } catch (Exceptions.InvalidIDException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEducationalCode() {
        return educationalCode;
    }

    public void setEducationalCode(String educationalCode) throws Exceptions.InvalidIDException {
        if (super.isValidId(educationalCode, getRole())) {
            this.educationalCode = educationalCode;
            System.out.println("ID Processed");
        } else {
            throw new Exceptions.InvalidIDException("please enter valid educational code");
        }
    }

    ////////////////////methods/////////////////

    public void AddQuiz(){
        System.out.println("Enter unit name");
        String unitname = scanner.next();
        boolean validunit = false;
        int unitindex = 0;
        for (Unit unit : units){
            if (unitname.equals(unit.getUnitname())){
                validunit = true;
                break;
            }
            unitindex++;
        }
        if (validunit){
            System.out.println("Enter start date , quiz time , finish date and quiz name :");
            units.get(unitindex).AddQuiz(scanner.next(), scanner.next(), scanner.next(), scanner.next());
            Main.firstmenu();
        } else {
            System.out.println("invalid unit name! do you want try again?y/n");
            if (scanner.next().charAt(0) == 'y'){
                AddQuiz();
            } else {
                System.out.println("Please enter your function number:");
                Main.setFunction(scanner.nextInt());
            }
        }
    }


    public void AddUnit() throws Exceptions.CustomArrayException {
        if (units.size() >= 10) {
            throw new Exceptions.CustomArrayException("Cannot add more units, the array is full!");
        } else {
            String unitname = scanner.next();
            units.add(new Unit(unitname, this));
            System.out.println("Unit Created!");
        }
    }

    public void deleteUnit() {
        try {
            String deleteunit = scanner.next();
            boolean unitFound = false;
            for (Unit unit : units) {
                if (unit.getUnitname().equals(deleteunit)) {
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

    public void AddStudent(String unitname, String username) {
        for (Student student : Main.students) {
            if (student.getUsername().equals(username)) {
                for (Unit unit : units) {
                    if (unit.getUnitname().equals(unitname)) {
                        unit.setStudents(student);
                        student.units.add(unit);
                        System.out.println("Student " + username + " Added!");
                        break;
                    }
                }
            }
        }
    }

    public void deleteStudent(String unitname, String username) {
        for (Student student : Main.students) {
            if (student.getUsername().equals(username)) {
                for (Unit unit : units) {
                    if (unit.getUnitname().equals(unitname)) {
                        unit.students.remove(student);
                        student.units.remove(unit);
                        System.out.println("Student " + username + " Removed!");
                        break;
                    }
                }
            }
        }
    }

    public void Addnotif(Unit unit) {
        String notif = scanner.next();
        unit.notifications.add(notif);
        System.out.println("notif Added!");
    }

    public void deletenotif(Unit unit) {
        String tag = scanner.next();
        for (String sms : unit.notifications) {
            if (sms.equals(tag)) {
                unit.notifications.remove(sms);
                System.out.println("notification Removed!");
                break;
            }
        }
    }

    @Override
    public void showclasses() {
        if (units.isEmpty()) {
            System.out.println("You dont have class!");
            System.out.println("Do you want create new class?(y/n)");
            String classop = scanner.next();
            if (classop.equals("y")) {
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
            String deleteans = scanner.next();
            if (deleteans.equals("y")) {
                deleteUnit();
            }
            System.out.println("Do you want to add student:(y/n)");
            String addop = scanner.next();
            if (addop.equals("y")) {
                String addunit = scanner.next();
                String addstudent = scanner.next();
                AddStudent(addunit, addstudent);
            }
            System.out.println("Do you want to delete student:(y/n)");
            String deleteop = scanner.next();
            if (deleteop.equals("y")) {
                String unitname = scanner.next();
                String studentname = scanner.next();
                deleteStudent(unitname, studentname);
            }
        }
        selectmenu();
    }

    @Override
    protected void showtasks() {

    }

    @Override
    public void shownotif() {
        for (Unit unit : units) {
            if (unit.notifications.isEmpty()) {
                System.out.println("You dont have notification!");
                System.out.println("Do you want create new notification?(y/n)");
                String addnotif = scanner.next();
                if (addnotif.equals("y")) {
                    Addnotif(unit);
                }
            } else {
                for (int i = 0; i < unit.notifications.size(); i++) {
                    if (unit.notifications.get(i) != null) {
                        System.out.println(unit.notifications.get(i));
                    }
                }
                System.out.println("Do you want delete notification?(y/n)");
                String deletenotif = scanner.next();
                if (deletenotif.equals("y")) {
                    deletenotif(unit);
                }
            }
        }
        selectmenu();
    }
}
