import java.util.Scanner;

public class Question extends Task{
    Scanner scanner = new Scanner(System.in);
    private String questiontext;
    private String answertext;
    private boolean done = false;

    // Constructor
    public Question(String deadline , String name ,String  questiontext,String answertext ,Unit unit) {
        super(deadline,name,unit);
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

}