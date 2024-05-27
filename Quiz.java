import java.text.Format;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.function.LongConsumer;

public class Quiz extends Task {

    private Date startdate;
    private Date quiztime;

    private int score;


    private HashMap<String, String> problem_answers = new HashMap<>();
    private HashMap<String, String> problem_student = new HashMap<>();

    public Quiz(String startdate, String quiztime,String deadline, String taskname, Unit unit, Teacher teacher ,int score) {
        super(deadline,taskname,teacher,unit);
        setStartdate(startdate);
        setQuiztime(quiztime);
        setScore(score);
    }

    public void setProblem_answers(String problem, String answer) {
        if (problem.isEmpty() || answer.isEmpty()) {
            System.out.println("problem text and answer text can't be empty");
            setProblem_answers(scanner.next(), scanner.next());
        } else {
            problem_answers.put(problem, answer);
        }
    }

    public void AnswerToQuiz() {
        int index = 0 ;
        System.out.println("enter answers :");
        int startminut = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        do {
            System.out.println((""+index+1)+") "+problem_answers.get(problem_answers.get(index))+" : \t\t score : "+score);
            String answer = scanner.nextLine();

            int answerminut = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
            int quiztime = Integer.parseInt( getQuizTime().split(":")[0] ) * 60 + Integer.parseInt( getQuizTime().split(":")[1] );

            if ( taskUnit.getTimeState(getQuizTime()) && (answerminut - startminut) < quiztime){
                problem_student.put( problem_answers.get(index), answer);
                index++;
            } else {
                System.out.println("The exam time is over.");
                break;
            }

        } while (index < problem_answers.size());
        System.out.println("Your answers saved successfully");
    }

    private void setStartdate(String date) {
        if (date.isEmpty()) {
            System.out.println("quiz start date can't be empty!");
            setStartdate(scanner.next());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            try {
                this.startdate = sdf.parse(date);
            } catch (ParseException e) {
                System.out.println("Date and time format incorrect!{31-10-1402 10:20}");
                setStartdate(scanner.next());
            }
        }
    }

    public void setQuiztime(String time) {
        if (time.isEmpty()) {
            System.out.println("quiz time can't be empty!");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            try {
                this.quiztime = sdf.parse(time);
            } catch (ParseException e) {
                System.out.println("quiz time format incorrect! Hours:Minutes");
                setQuiztime(scanner.next());
            }
        }
    }

    private void setDeadline(String fdate) {
        if (fdate.isEmpty()) {
            System.out.println("deadline can't be empty!try again");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            try {
                this.deadline = sdf.parse(fdate);
            } catch (ParseException e) {
                System.out.println("deadline and time is incorrect!Day-Month-Year Hours:Minutes");
                setDeadline(scanner.next());
            }
        }
    }

    public String getFdate() {
        Format formater = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return formater.format(super.getDeadlineDate());
    }
    public String getSdate() {
        Format formater = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return formater.format(startdate);
    }

    public String getQuizTime(){
        Format formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(quiztime);
    }
    public void setScore(int score) {
        if ( score >= 0) {
            this.score = score;
        } else {
            System.out.println("score number invalid! try again");
            setScore(scanner.nextInt());
        }
    }
}
