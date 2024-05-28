import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Question extends Task {
    private String questionText;
    private int answer;
    private final HashMap<Integer, String> questionsAnswer = new HashMap<>();
    private final HashMap<Student, Integer> studentAnswer = new HashMap<>();
    private final HashMap<String, Integer> studentScores = new HashMap<>();

    // Constructor
    public Question(String deadline, String questionText, int answer, String taskName, Unit unit, Teacher teacher) {
        super(deadline, taskName, teacher, unit);
        setQuestionText(questionText);
        setAnswer(answer);
    }

    public HashMap<Integer, String> getQuestionsAnswer() {
        return questionsAnswer;
    }

    public HashMap<Student, Integer> getStudentAnswer() {
        return studentAnswer;
    }

    public HashMap<String, Integer> getStudentScores() {
        return studentScores;
    }

    public void answerToQuestion(Student student, Scanner scanner) {
        System.out.println("Problem: \n" + questionText + "\nEnter your answer: ");
        int index = 0;
        for (String text : questionsAnswer.values()) {
            System.out.println(++index + ")" + text);
        }
        System.out.println("Choose the correct answer:");
        int answer = scanner.nextInt();
        studentAnswer.put(student, answer);
        System.out.println("Your answer has been saved successfully.");
    }

    // Setter and Getter for questionText
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

    // Setter and Getter for answer
    public void setAnswer(int answer) {
        while (answer <= 0) {
            System.out.println("Answer can't be empty or zero! Please enter a valid answer:");
            answer = new Scanner(System.in).nextInt(); // Temporary scanner to get input
        }
        this.answer = answer;
    }

    public int getAnswer() {
        return this.answer;
    }

    public String getFormattedDate() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(super.getDeadlineDate());
    }
}
