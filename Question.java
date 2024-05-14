import java.util.Scanner;

public class Question {
    Scanner scanner = new Scanner(System.in);
    Task task;
    String[][] question_answer;
    private int score;
    private Student student;
    private String questiontext;

    // Constructor
    public Question( Task task , int score ) {
        setTask(task);
        setScore(score);
    }

    // Setter And Getters
    private void setTask(Task t) {
        if (t != null){
            this.task = t;
        }
    }

    public void setScore(int score) {
        if (score >= 0 ) {
            this.score = score;
        } else {
            System.out.println("Enter valid score for this question");
            setScore( scanner.nextInt() );
        }
    }
    public void setQuestiontext(String text) {
        if (!text.isEmpty()){
            this.questiontext = text;
        } else {
            System.out.println("text can't be empty!");
            setQuestiontext(scanner.next());
        }
    }

}