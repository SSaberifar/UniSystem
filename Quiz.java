import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents a Quiz task, which is a type of Task assigned by a Teacher to Students.
 */
public class Quiz extends Task {
    private Date startDate;
    private Date quizTime;
    private int score;
    private String quizText;
    private String quizAnswer;

    private final List<String> answers = new ArrayList<>(4);
    private final Map<Student, String> studentAnswers = new HashMap<>();
    private final List<Map<Student, String>> studentsHolder = new ArrayList<>();
    private final Map<String, Integer> studentsScore = new HashMap<>();

    private static final Scanner scanner = new Scanner(System.in);

    public Quiz(String startDate, String quizTime, String deadline, String taskName, Unit unit, Teacher teacher, int score) {
        super(deadline, taskName, teacher, unit);
        setStartDate(startDate);
        setQuizTime(quizTime);
        setScore(score);
    }

    public Map<Student, String> getStudentAnswers() {
        return studentAnswers;
    }

    public Map<String, Integer> getStudentsScore() {
        return studentsScore;
    }

    public void setProblemAnswers(String problem, String answer) {
        if (problem.isEmpty() || answer.isEmpty()) {
            throw new IllegalArgumentException("Problem text and answer text can't be empty.");
        }
        this.quizText = problem;
        System.out.println("Enter 4 answer options:");
        for (int i = 0; i < 4; i++) {
            answers.add(scanner.next());
        }
        this.quizAnswer = answer;
    }

    public void answerToQuiz(Student student) {
        System.out.println("Enter your answer:");
        String answer = scanner.nextLine();
        LocalDateTime now = LocalDateTime.now();
        int startMinute = now.getHour() * 60 + now.getMinute();
        int totalQuizTime = getMinutesFromFormattedTime(getQuizTime());

        int answerMinute = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        if ((answerMinute - startMinute) < totalQuizTime) {
            studentAnswers.put(student, answer);
            System.out.println("Your answers were saved successfully.");
        } else {
            System.out.println("The exam time is over.");
        }
        studentsHolder.add(new HashMap<>(studentAnswers));
    }

    public void setStartDate(String date) {
        this.startDate = parseDate(date, "yyyy-MM-dd HH:mm", "Quiz start date");
    }

    public void setQuizTime(String time) {
        this.quizTime = parseDate(time, "HH:mm", "Quiz time");
    }

    @Override
    public void setDeadline(String deadline) {
        Date deadlineDate = parseDate(deadline, "yyyy-MM-dd HH:mm", "Deadline");
        super.setDeadline(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(deadlineDate));
    }

    private Date parseDate(String date, String pattern, String fieldName) {
        while (date.isEmpty()) {
            System.out.println(fieldName + " can't be empty. Enter the " + fieldName.toLowerCase() + ":");
            date = scanner.next();
        }
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            System.out.println(fieldName + " format incorrect! Use format: " + pattern);
            return parseDate(scanner.next(), pattern, fieldName);
        }
    }

    private int getMinutesFromFormattedTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public String getFormattedDate(Date date, String pattern) {
        Format formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public String getFormattedStartDate() {
        return getFormattedDate(startDate, "yyyy-MM-dd HH:mm");
    }

    public String getQuizTime() {
        return getFormattedDate(quizTime, "HH:mm");
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getQuizText() {
        return quizText;
    }

    public String getQuizAnswer() {
        return quizAnswer;
    }

    @Override
    public String toString() {
        return String.format("Quiz{%nstartDate=%s%nquizTime=%s%nscore=%d%nquizText='%s'%nquizAnswer='%s'%nstudentAnswers=%s%nstudentsHolder=%s%nstudentsScore=%s%n}",
                startDate, quizTime, score, quizText, quizAnswer, studentAnswers, studentsHolder, studentsScore);
    }
}
