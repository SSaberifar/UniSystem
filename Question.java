import java.util.Scanner;

public class Question {
    Scanner scanner = new Scanner(System.in);
    Task task;
    String[][] question_answer;
    private int score;
    private Student student;

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

}