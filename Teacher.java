import java.util.Scanner;

public class Teacher extends Person {
    public Teacher(String firstName, String lastName, String username, String email, String phoneNumber, String role, String pass, String educationalID) {
        super(firstName, lastName, username, email, phoneNumber, role, pass, educationalID);
    }

    // Methods
    public void addQuestion(String deadline, String taskName, String questionText, String answer, Unit unit) {
        Scanner scanner = new Scanner(System.in);
        unit.getTasks().add(new Question(deadline, questionText, answer, taskName, unit, this));
        System.out.println("Question added. Do you want to add another question (y/n)?");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("Enter date, then question name, text, and answer:");
            addQuestion(scanner.next(), scanner.next(), scanner.nextLine(), scanner.nextLine(), unit);
        }
        Main.printMenu();
    }

    public void addQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter unit name:");
        String unitName = scanner.next();
        Unit unit = findUnitByName(unitName);
        if (unit != null) {
            System.out.println("Enter start date, quiz time, finish date, quiz name, quiz text, answers and correct answer:");
            System.out.println("Enter start date:");
            String startDate = scanner.nextLine();
            System.out.println("Enter quiztime:");
            String quizTime = scanner.next();
            scanner.nextLine();
            System.out.println("Enter finish date:");
            String finishDate = scanner.nextLine();
            System.out.println("Enter quizname:");
            String quizName = scanner.next();
            System.out.println("Enter quizscore:");
            int score = scanner.nextInt();
            scanner.nextLine();
            unit.addQuiz(startDate, quizTime, finishDate, quizName, score);
            Main.printMenu();
        } else {
            retryAddQuiz();
        }
    }

    public void retryAddQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Invalid unit name! Do you want to try again (y/n)?");
        if (scanner.next().equalsIgnoreCase("y")) {
            addQuiz();
        } else {
            Main.printMenu();
        }
    }

    public void addUnit() throws Exceptions.CustomArrayException {
        Scanner scanner = new Scanner(System.in);
        if (units.size() >= 10) {
            throw new Exceptions.CustomArrayException("Cannot add more units, the array is full!");
        }
        System.out.println("Enter unit name:");
        String unitName = scanner.next();
        units.add(new Unit(unitName, this));
        System.out.println("Unit Created!");
    }

    public void deleteUnit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter unit name to delete:");
        String deleteUnitName = scanner.next();
        Unit unitToDelete = findUnitByName(deleteUnitName);
        if (unitToDelete != null) {
            units.remove(unitToDelete);
            System.out.println("Unit Removed!");
        } else {
            System.out.println("Unit not found!");
        }
    }

    public void addStudent(String unitName, String username) {
        Student student = Main.students.stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (student != null) {
            Unit unit = findUnitByName(unitName);
            if (unit != null) {
                unit.addStudent(student);
                System.out.println("Student " + username + " Added!");
            }
        }
    }

    public void deleteStudent(String unitName, String username) {
        Student student = Main.students.stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (student != null) {
            Unit unit = findUnitByName(unitName);
            if (unit != null) {
                unit.getStudents().remove(student);
                System.out.println("Student " + username + " Removed!");
            }
        }
    }

    public void addNotification(Unit unit) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter notification:");
        String notification = scanner.nextLine();
        unit.getNotifications().add(notification);
        System.out.println("Notification Added!");
    }

    public void deleteNotification(Unit unit) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter notification to delete:");
        String tag = scanner.nextLine();
        if (unit.getNotifications().remove(tag)) {
            System.out.println("Notification Removed!");
        } else {
            System.out.println("Notification not found!");
        }
    }

    @Override
    public void showClasses() {
        Scanner scanner = new Scanner(System.in);
        if (units.isEmpty()) {
            System.out.println("You don't have any classes! Do you want to create a new class? (y/n)");
            if (scanner.next().equalsIgnoreCase("y")) {
                try {
                    addUnit();
                } catch (Exceptions.CustomArrayException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            units.forEach(unit -> System.out.println((units.indexOf(unit) + 1) + "- " + unit.getUnitName()));
            manageClasses(scanner);
        }
        selectMenu();
    }

    public void manageClasses(Scanner scanner) {
        System.out.println("Do you want to create a new class? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            try {
                addUnit();
            } catch (Exceptions.CustomArrayException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Do you want to delete a class? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            deleteUnit();
        }
        manageStudents(scanner);
    }

    public void manageStudents(Scanner scanner) {
        System.out.println("Do you want to add a student? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("Enter unit name:");
            String addUnit = scanner.next();
            System.out.println("Enter student username:");
            String addStudent = scanner.next();
            addStudent(addUnit, addStudent);
        }
        System.out.println("Do you want to delete a student? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("Enter unit name:");
            String unitName = scanner.next();
            System.out.println("Enter student username:");
            String studentName = scanner.next();
            deleteStudent(unitName, studentName);
        }
    }

    @Override
    protected void showTasks() {
        Scanner scanner = new Scanner(System.in);
        units.forEach(unit -> {
            if (unit.getTasks().isEmpty()) {
                System.out.println("You don't have any tasks. Do you want to create a new task? (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    createTask(scanner, unit);
                }
            } else {
                displayTasks(unit);
            }
        });
    }

    public void createTask(Scanner scanner, Unit unit) {
        System.out.println("Which task do you want to create? (Quiz, Question)");
        String taskType = scanner.next();
        scanner.nextLine();
        if (taskType.equalsIgnoreCase("Quiz")) {
            addQuiz();
        } else {
            System.out.println("Enter deadline:");
            String deadline = scanner.nextLine();
            System.out.println("Enter task name:");
            String taskName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter question text:");
            String questionText = scanner.nextLine();
            System.out.println("Enter answer text:");
            String answer = scanner.nextLine();
            addQuestion(deadline, taskName, questionText, answer, unit);
        }
    }

    public void displayTasks(Unit unit) {
        unit.getTasks().forEach(task -> {
            if (task instanceof Quiz quiz) {
                System.out.println("Quiz name: " + quiz.getName() + ", deadline: " + quiz.getFdate());
                autoCorrectQuiz(quiz);
            } else if (task instanceof Question question) {
                System.out.println("Problem text: " + question.getQuestionText() + ", answer text: " + question.getAnswer() + ", deadline: " + question.getFormattedDate());
                if (!unit.isDeadlinePassed(question.getFormattedDate())) {
                    System.out.println("Students' answers are ready!");
                    autoCorrectQuestion(question);
                }
            }
        });
    }

    @Override
    public void showNotification() {
        Scanner scanner = new Scanner(System.in);
        if (units.isEmpty()){
            System.out.println("You don't have any unit , please add unit then try again");
        }else {
            units.forEach(unit -> {
                if (unit.getNotifications().isEmpty()) {
                    System.out.println("You don't have any notifications. Do you want to create a new notification? (y/n)");
                    if (scanner.next().equalsIgnoreCase("y")) {
                        addNotification(unit);
                    }
                } else {
                    unit.getNotifications().forEach(System.out::println);
                    manageNotifications(scanner, unit);
                }
            });
        }
        selectMenu();
    }

    public void manageNotifications(Scanner scanner, Unit unit) {
        System.out.println("Do you want to delete a notification? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            deleteNotification(unit);
        }
    }

    public void autoCorrectQuiz(Quiz quiz) {
        System.out.println("Quiz text: " + quiz.getQuizTxt() + "\nAutocorrecting...");
        quiz.getStudentAnswers().forEach((student, answer) -> {
            if (quiz.getQuizAns().equals(answer)) {
                quiz.getStudentsScore().put(student.getEducationalID(), quiz.getScore());
            } else {
                quiz.getStudentsScore().put(student.getEducationalID(), 0);
            }
        });
        System.out.println("Autocorrecting finished. Students' scores saved.");
    }

    public void autoCorrectQuestion(Question question) {
        System.out.println("Question text: " + question.getQuestionText() + "\nAutocorrecting...");
        question.getStudentAnswer().forEach((student, answer) -> {
            if (question.getAnswer().equals(answer)) {
                question.getStudentScores().put(student.getEducationalID(), 20);
            } else {
                question.getStudentScores().put(student.getEducationalID(), 0);
            }
        });
        System.out.println("Autocorrecting finished. Students' scores saved.");
    }

    public Unit findUnitByName(String unitName) {
        return units.stream()
                .filter(unit -> unit.getUnitName().equals(unitName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "units=" + units +
                '}';
    }
}
