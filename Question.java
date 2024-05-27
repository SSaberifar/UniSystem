import java.text.Format;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Question extends Task {
    Scanner scanner = new Scanner(System.in);
    private String questiontext;
    private String answertext;
    private String studentanswer;

    // Constructor
    public Question(String deadline, String questiontext, String answertext, String taskname,Unit unit, Teacher teacher) {
        super(deadline, taskname, teacher,unit);
        setQuestiontext(questiontext);
        setAnswertext(answertext);
    }

    public void AnswerToQuestion(){
        System.out.println("problem : \n"+questiontext +"\n enter your answer : ");
        String answer = scanner.nextLine();
        studentanswer = answer;
        System.out.println("your answer saved successfully");
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
            this.answertext = answertext;
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
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(super.getDeadlineDate());
    }
}