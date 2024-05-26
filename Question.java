import java.text.Format;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Question extends Task {
    Scanner scanner = new Scanner(System.in);
    private String questiontext;
    private String answertext;
    private final boolean done = false;

    // Constructor
    public Question(String deadline, String questiontext, String answertext, String taskname,Unit unit, Teacher teacher) {
        super(deadline, taskname, unit,teacher);
        setQuestiontext(questiontext);
        setAnswertext(answertext);
    }

    // Setter And Getters

    public void setQuestiontext(String text) {
        if (!text.isEmpty()) {
            this.questiontext = text;
        } else {
            System.out.println("text can't be empty!");
            setQuestiontext(scanner.next());
        }
    }

    public void setAnswertext(String answertext) {
        if (!answertext.isEmpty()) {
            this.questiontext = answertext;
        } else {
            System.out.println("answer can't be empty!");
            setAnswertext(scanner.next());
        }
    }

    public String getQuestiontext(){
        return this.questiontext;
    }
    public String getAnswertext(){
        return this.answertext;
    }

    public String getFdate(){
        Format formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(super.getDeadlineDate());
    }
}