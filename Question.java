import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Question extends Task {
    private String questionText;
    private String answer;
    private final HashMap<String, String> studentAnswer = new HashMap<>();
    private final HashMap<String, Integer> studentScores = new HashMap<>();

    // Constructor
    public Question(String deadline, String questionText, String answer, String taskName, Unit unit, Teacher teacher) {
        super(deadline, taskName, teacher, unit);
        setQuestionText(questionText);
        setAnswer(answer);
    }

    public HashMap<String, String> getStudentAnswer() {
        return studentAnswer;
    }

    public HashMap<String, Integer> getStudentScores() {
        return studentScores;
    }

    public void answerToQuestion(Student student, Scanner scanner) {
        System.out.println("Problem: " + questionText + " Enter your answer:");
        String answer = scanner.nextLine(); // خطا از این خط حذف شده است
        studentAnswer.put(student.getUsername(), answer);
        System.out.println("Your answer has been saved successfully.");
    }

    public void setQuestionText(String text) {
        while (text.isEmpty()) {
            System.out.println("Text can't be empty! Please enter the text:");
            text = new Scanner(System.in).nextLine(); // Temporary scanner to get input
        }
        this.questionText = text;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setAnswer(String answer) {
        while (answer == null) {
            System.out.println("Answer can't be empty! Please enter a valid answer:");
            answer = new Scanner(System.in).nextLine();
        }
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getFormattedDate() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(super.getDeadlineDate());
    }

    @Override
    public String toString() {
        return "Question{\n" +
                "question deadline=" + super.getDeadlineDate() + '\n' +
                "question name=" + super.getName() + '\n' +
                "question text=" + questionText + '\n' +
                "question answer=" + answer + '\n' +
                "student answer=" + studentAnswer + '\n' +
                "student scores=" + studentScores + '\n' +
                '}';
    }
}
