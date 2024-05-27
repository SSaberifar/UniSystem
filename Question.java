import java.text.Format;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Question extends Task {
    Scanner scanner = new Scanner(System.in);
    String questiontext;
    int answer;

    HashMap<Integer,String> questions_answer = new HashMap<>();
    HashMap<Student,Integer> studentanswer = new HashMap<>();
    HashMap<String,Integer> students_score = new HashMap<>();

    // Constructor
    public Question(String deadline, String questiontext, int answer, String taskname,Unit unit, Teacher teacher) {
        super(deadline, taskname, teacher,unit);
        setQuestiontext(questiontext);
        setAnswertext(answer);
    }

    public void AnswerToQuestion(Student student){
        System.out.println("problem : \n"+questiontext +"\n enter your answer : ");
        int index =0;
        for (String text : questions_answer.values()){
            System.out.println(++index +")"+ text);
        }
        System.out.println("choose correct answer :");
        int answer = scanner.nextInt();
        studentanswer.put(student,answer);
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

    public void setAnswertext(int answer) {
        if (answer > 0 ) {
            this.answer = answer;
        } else {
            System.out.println("answer can't be empty!");
            setAnswertext(scanner.nextInt());
        }
    }

    public String getQuestiontext(){
        return this.questiontext;
    }
    public int getAnswertext(){
        return this.answer;
    }

    public String getFdate(){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(super.getDeadlineDate());
    }
}