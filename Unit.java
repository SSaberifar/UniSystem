import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Unit {

    private static final int MAX_STUDENTS = 10;
    private static final int MAX_NOTIFICATIONS = 10;
    private static final int MAX_TASKS = 20;

    private String unitName;
    private List<Student> students = new ArrayList<>(MAX_STUDENTS);
    private List<String> notifications = new ArrayList<>(MAX_NOTIFICATIONS);
    private List<Task> tasks = new ArrayList<>(MAX_TASKS);
    private int currentQuizIndex = 0;
    private Teacher teacher;

    public Unit(String unitName, Teacher teacher) {
        setUnitName(unitName);
        setTeacher(teacher);
    }

    // Methods
    public void addQuestion(String deadline, String taskName, String questionText, int answer) {
        Scanner scanner = new Scanner(System.in);
        if (isValidQuestion(deadline, taskName, questionText, answer)) {
            tasks.add(new Question(deadline, questionText, answer, taskName, this, this.teacher));
            System.out.println("Question added. Do you want to add another question? (y/n)");
            if (scanner.next().equalsIgnoreCase("y")) {
                promptForQuestionDetails();
            }
        } else {
            System.out.println("Deadline and question name can't be empty. Do you want to try again? (y/n)");
            if (scanner.next().equalsIgnoreCase("y")) {
                promptForQuestionDetails();
            }
        }
    }

    private boolean isValidQuestion(String deadline, String taskName, String questionText, int answer) {
        return !deadline.isEmpty() && !taskName.isEmpty() && !questionText.isEmpty() && answer > 0;
    }

    private void promptForQuestionDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date, then question name, text, and answer:");
        addQuestion(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
    }

    public void addQuiz(String startDate, String quizTime, String deadline, String quizName, int score) {
        Scanner scanner = new Scanner(System.in);
        if (isInvalidQuizDetails(startDate, quizTime, deadline, quizName)) {
            System.out.println("Start/deadline date and quiz date/name can't be empty! Try again:");
            addQuiz(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
        } else {
            tasks.add(new Quiz(startDate, quizTime, deadline, quizName, this, this.teacher, score));
            currentQuizIndex++;
            addQuizQuestions(scanner);
        }
    }

    private boolean isInvalidQuizDetails(String startDate, String quizTime, String deadline, String quizName) {
        return startDate.isEmpty() || quizTime.isEmpty() || deadline.isEmpty() || quizName.isEmpty();
    }

    private void addQuizQuestions(Scanner scanner) {
        boolean repeat;
        do {
            repeat = false;
            System.out.println("Enter your questions and answers:");
            if (tasks.get(currentQuizIndex) instanceof Quiz quizInstance) {
                quizInstance.setProblemAnswers(scanner.next(), scanner.next());
                System.out.println("Question and answer added successfully. Do you want to add another problem? (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    repeat = true;
                }
            }
        } while (repeat);
    }

    public boolean isDeadlinePassed(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime finishTime = LocalDateTime.parse(deadline, formatter);
        Duration duration = Duration.between(LocalDateTime.now(), finishTime);

        if (finishTime.isAfter(LocalDateTime.now())) {
            System.out.println("You have " + duration.toHours() + " hours and " + (duration.toMinutes() % 60) + " minutes remaining.");
            return false;
        } else {
            System.out.println(duration.toHours() + " hours and " + (duration.toMinutes() % 60) + " minutes have passed since the deadline!");
            return true;
        }
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        if (unitName.isEmpty()) {
            throw new IllegalArgumentException("Unit name can't be empty!");
        }
        this.unitName = unitName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (students.size() < MAX_STUDENTS) {
            students.add(student);
        } else {
            throw new IllegalStateException("Maximum number of students reached.");
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    private void setTeacher(Teacher teacher) {
        if (teacher != null) {
            this.teacher = teacher;
        } else {
            throw new IllegalArgumentException("Teacher cannot be null!");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return unitName;
    }

    public List<String> getNotifications() {
        return notifications;
    }
}
