import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Question extends Task {
    private String questionText;
    private String answer;
    private final HashMap<String, String> studentAnswers = new HashMap<>();
    private final HashMap<String, Integer> studentScores = new HashMap<>();

    public Question(String deadline, String questionText, String answer, String taskName, Unit unit, Teacher teacher) {
        super(deadline, taskName, teacher, unit);
        setQuestionText(questionText);
        setAnswer(answer);
    }

    public HashMap<String, String> getStudentAnswers() {
        return studentAnswers;
    }

    public HashMap<String, Integer> getStudentScores() {
        return studentScores;
    }

    public void answerToQuestion(Student student, Scanner scanner) {
        System.out.println("Problem: " + questionText + " Enter your answer:");
        String answer = scanner.nextLine();
        studentAnswers.put(student.getUsername(), answer);
        System.out.println("Your answer has been saved successfully.");
    }

    public void setQuestionText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Question text can't be empty!");
        }
        this.questionText = text;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer can't be empty!");
        }
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFormattedDate() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(super.getDeadlineDate());
    }

    @Override
    public String toString() {
        return String.format(
                "Question{\nquestion deadline=%s\nquestion name=%s\nquestion text=%s\nquestion answer=%s\nstudent answers=%s\nstudent scores=%s\n}",
                super.getDeadlineDate(), super.getName(), questionText, answer, studentAnswers, studentScores
        );
    }
}
