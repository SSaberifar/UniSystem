import java.util.Scanner;

public class Teacher extends Person {

    Scanner scanner = new Scanner(System.in);

    public Teacher(String firstName, String lastname, String username, String email, String phoneNumber, String role, String pass, String educationalID) {
        super(firstName, lastname, username, email, phoneNumber, role, pass, educationalID);
    }


    ////////////////////methods/////////////////

    public void AddQuestion(String deadline, String taskname, String questiontext, String answertext, Unit unit) {
        unit.tasks.add(new Question(deadline, questiontext, answertext, taskname, unit, this));
        System.out.println("question added , do you want to add another question(y/n)");
        String questionBool = scanner.next();
        if (questionBool.equals("y")) {
            System.out.println("enter date, then question name ,text and answer");
            String queadead = scanner.next();
            String queaname = scanner.next();
            scanner.nextLine(); // Consume the newline
            String queatext = scanner.nextLine();
            String queaans = scanner.nextLine();
            AddQuestion(queadead, queaname, queatext, queaans, unit);
        }
        Main.printMenu();
    }

    public void AddQuiz() {
        System.out.println("Enter unit name");
        String unitName = scanner.next();
        scanner.nextLine(); // Consume the newline
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
            System.out.println("Enter start date, quiz time, finish date and quiz name and score:");
            String startDate = scanner.next();
            String quizTime = scanner.next();
            String finishDate = scanner.next();
            String quizName = scanner.next();
            int score = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            units.get(unitIndex).AddQuiz(startDate, quizTime, finishDate, quizName,score);
            Main.printMenu();
        } else {
            System.out.println("invalid unit name! do you want try again?y/n");
            if (scanner.next().charAt(0) == 'y') {
                scanner.nextLine(); // Consume the newline
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
            scanner.nextLine(); // Consume the newline
            units.add(new Unit(unitName, this));
            System.out.println("Unit Created!");
        }
    }

    public void deleteUnit() {
        try {
            System.out.println("Enter unit name to delete:");
            String deleteUnit = scanner.next();
            scanner.nextLine(); // Consume the newline
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
            scanner.nextLine(); // Consume the newline
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
            scanner.nextLine(); // Consume the newline
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
            scanner.nextLine(); // Consume the newline
            if (deleteOperation.equals("y")) {
                deleteUnit();
            }
            System.out.println("Do you want to add a student? (y/n)");
            String addOperation = scanner.next();
            scanner.nextLine(); // Consume the newline
            if (addOperation.equals("y")) {
                System.out.println("Enter unit name:");
                String addUnit = scanner.next();
                System.out.println("Enter student username:");
                String addStudent = scanner.next();
                scanner.nextLine(); // Consume the newline
                AddStudent(addUnit, addStudent);
            }
            System.out.println("Do you want to delete a student? (y/n)");
            String deleteStudent = scanner.next();
            scanner.nextLine(); // Consume the newline
            if (deleteStudent.equals("y")) {
                System.out.println("Enter unit name:");
                String unitName = scanner.next();
                System.out.println("Enter student username:");
                String studentName = scanner.next();
                scanner.nextLine(); // Consume the newline
                deleteStudent(unitName, studentName);
            }
        }
        selectMenu();
    }

    @Override
    protected void showTasks() {
        for (Unit unit : units) {
            if (unit.tasks.isEmpty()) {
                System.out.println("You dont have any task");
                System.out.println("do you want to create new task?(y/n)");
                String taskAnswer = scanner.next();
                scanner.nextLine(); // Consume the newline
                if (taskAnswer.equals("y")) {
                    System.out.println("which task do you want to create?(Quiz,Question)");
                    String question = scanner.next();
                    scanner.nextLine(); // Consume the newline
                    if (question.equals("Quiz")) {
                        AddQuiz();
                    } else {
                        System.out.println("Enter deadline:");
                        String deadline = scanner.nextLine();
                        scanner.nextLine(); // Consume the newline
                        System.out.println("Enter task name:");
                        String taskname = scanner.next();
                        scanner.nextLine(); // Consume the newline
                        System.out.println("Enter question text:");
                        String questiontext = scanner.nextLine();
                        System.out.println("Enter answer text:");
                        String answertext = scanner.nextLine();
                        AddQuestion(deadline, taskname, questiontext, answertext, unit);
                    }
                    break;
                } else {
                    selectMenu();
                }
            } else {
                System.out.println("unit " + unit.getName() + " tasks :");
                for (Task task : unit.tasks) {
                    if (task instanceof Quiz quizInstance) {
                        System.out.println("Quiz name : " + quizInstance.getName() + " deadline : " + quizInstance.getFdate());
                    }
                }
                System.out.println("---------------------------");
                for (Task task : unit.tasks) {
                    if (task instanceof Question question) {
                        System.out.println("problem text : " + question.getQuestiontext() + " answer text : " + question.getAnswertext() + " deadline : " + question.getFdate());
                        if (!unit.getTimeState(question.getFdate())) {
                            System.out.println("Students answers are ready!");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void showNotification() {
        for (Unit unit : units) {
            if (unit.notifications.isEmpty()) {
                System.out.println("You don't have any notifications!");
                System.out.println("Do you want to create a new notification? (y/n)");
                String addNotification = scanner.next();
                scanner.nextLine(); // Consume the newline
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
                scanner.nextLine(); // Consume the newline
                if (deleteNotification.equals("y")) {
                    deleteNotification(unit);
                }
            }
        }
        selectMenu();
    }

    public void Correcting(String unitname , String taskname) {

        for (Unit unit : units){
            if (unit.getName().equals(unitname)){
                for (Task task : unit.tasks){
                    if (task.equals(task) && task instanceof Quiz quiz ){

                    }
                }
            }
        }
        System.out.println("Correct students answers :");
    }
}
