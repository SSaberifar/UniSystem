import java.util.Scanner;

public class Student extends Person {
    private String major;

    public Student(String firstName, String lastName, String username, String email, String phoneNumber, String role, String pass, String educationalID, String studyField) {
        super(firstName, lastName, username, email, phoneNumber, role, pass, educationalID);
        setStudyField(studyField);
    }

    public String getStudyField() {
        return major;
    }

    public void setStudyField(String studyField) {
        if (super.isValidString(studyField)) {
            this.major = studyField;
        } else {
            System.out.println("Study field is not valid");
        }
    }

    @Override
    public void showClasses() {
        if (units.isEmpty()) {
            System.out.println("You don't have any classes!");
            selectMenu();
        } else {
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                if (unit != null) {
                    System.out.println((i + 1) + ". " + unit.getUnitName());
                }
            }
        }
    }

    @Override
    protected void showTasks() {
        if (units.isEmpty()) {
            System.out.println("You don't have any units.");
            selectMenu();
        } else {
            for (Unit unit : units) {
                displayUnitTasks(unit);
            }
            promptToAnswerTask();
        }
    }

    private void displayUnitTasks(Unit unit) {
        System.out.println("Unit: " + unit.getName() + " tasks:");
        for (Task task : unit.getTasks()) {
            if (task instanceof Quiz quiz) {
                System.out.println("Quiz name: " + quiz.getName() + ", deadline: " + quiz.getFdate());
            }
        }
        System.out.println("---------------------------");
        for (Task task : unit.getTasks()) {
            if (task instanceof Question question) {
                System.out.println("Question name: " + question.getName() + ", deadline: " + question.getFormattedDate());
            }
        }
        System.out.println("---------------------------");
    }

    private void promptToAnswerTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to answer any task? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("Enter unit name:");
            String unitName = scanner.next();
            for (Unit unit : units) {
                if (unit.getName().equalsIgnoreCase(unitName)) {
                    answerTask(unit);
                    break;
                }
            }
        }
    }

    private void answerTask(Unit unit) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your task name:");
        String taskName = scanner.next();
        for (Task task : unit.getTasks()) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                handleTaskAnswering(task, unit);
                return;
            }
        }
        System.out.println("There isn't any task with the name " + taskName + "!");
        selectMenu();
    }

    private void handleTaskAnswering(Task task, Unit unit) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to start answering? (y/n)");
        String accept = scanner.next();
        if (accept.equalsIgnoreCase("y")) {
            if (task instanceof Quiz quiz && unit.isDeadlinePassed(quiz.getFdate())) {
                System.out.println("Quiz name: " + quiz.getName() + ", total time: " + quiz.getQuizTime());
                quiz.answerToQuiz(this);
            } else if (task instanceof Question question && !unit.isDeadlinePassed(question.getFormattedDate())) {
                System.out.println("Question name: " + question.getName() + ", deadline: " + question.getFormattedDate());
                question.answerToQuestion(this, new Scanner(System.in));
            }
        }
        selectMenu();
    }

    @Override
    public void showNotification() {
        for (Unit unit : units) {
            displayUnitNotifications(unit);
        }
    }

    private void displayUnitNotifications(Unit unit) {
        if (unit.getNotifications().isEmpty()) {
            System.out.println("You don't have any notifications!");
            selectMenu();
        } else {
            System.out.println(unit.getUnitName() + ":");
            for (int i = 0; i < unit.getNotifications().size(); i++) {
                System.out.println((i + 1) + ". " + unit.getNotifications().get(i));
            }
        }
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Study Field: " + getStudyField());
    }

    @Override
    public String toString() {
        return super.toString() +
                "major='" + major + '\'' +
                '}';
    }
}
