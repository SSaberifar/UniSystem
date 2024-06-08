import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Unit {

    private String unitName;
    private final List<Student> students = new ArrayList<>();
    private final List<String> notifications = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private Teacher teacher;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Scanner scanner = new Scanner(System.in);

    public Unit(String unitName, Teacher teacher) {
        setUnitName(unitName);
        setTeacher(teacher);
    }

    public void addQuestion(String deadline, String taskName, String questionText, String answer) {
        if (isValidQuestion(deadline, taskName, questionText, answer)) {
            tasks.add(new Question(deadline, questionText, answer, taskName, this, this.teacher));
            System.out.println("Question added. Do you want to add another question? (y/n)");
            if (scanner.next().equalsIgnoreCase("y")) {
                promptForQuestionDetails();
            }
        } else {
            System.out.println("Invalid input. Deadline, question name, text, and answer can't be empty. Do you want to try again? (y/n)");
            if (scanner.next().equalsIgnoreCase("y")) {
                promptForQuestionDetails();
            }
        }
    }

    private boolean isValidQuestion(String deadline, String taskName, String questionText, String answer) {
        return !deadline.isEmpty() && !taskName.isEmpty() && !questionText.isEmpty() && !answer.isEmpty();
    }

    private void promptForQuestionDetails() {
        System.out.println("Enter deadline (yyyy-MM-dd HH:mm), question name, text, and answer:");
        addQuestion(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
    }

    public void addQuiz(String startDate, String quizTime, String deadline, String quizName, int score) {
        if (isInvalidQuizDetails(startDate, quizTime, deadline, quizName)) {
            System.out.println("Invalid input. Start date, quiz time, deadline, and quiz name can't be empty. Try again:");
            addQuiz(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
        } else {
            Quiz quiz = new Quiz(startDate, quizTime, deadline, quizName, this, this.teacher, score);
            tasks.add(quiz);
            addQuizQuestions(quiz);
        }
    }

    private boolean isInvalidQuizDetails(String startDate, String quizTime, String deadline, String quizName) {
        return startDate.isEmpty() || quizTime.isEmpty() || deadline.isEmpty() || quizName.isEmpty();
    }

    private void addQuizQuestions(Quiz quiz) {
        boolean repeat;
        do {
            repeat = false;
            System.out.println("Enter your questions and answers:");
            quiz.setProblemAnswers(scanner.nextLine(), scanner.nextLine());
            System.out.println("Question and answer added successfully. Do you want to add another problem? (y/n)");
            if (scanner.next().equalsIgnoreCase("y")) {
                repeat = true;
            }
        } while (repeat);
    }

    public boolean isDeadlinePassed(String deadline) {
        LocalDateTime finishTime = LocalDateTime.parse(deadline, DATE_FORMAT);
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
        this.students.add(student);
        student.units.add(this);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    private void setTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null!");
        }
        this.teacher = teacher;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public String getStudentsAsString() {
        return String.join(", ", students.stream().map(Student::getUsername).toList());
    }

    @Override
    public String toString() {
        return String.format("%s:%nteacher= %s%nStudents= %s", unitName, teacher.getUsername(), getStudentsAsString());
    }
}
