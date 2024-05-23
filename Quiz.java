import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Quiz extends Unit {

    private Date startdate;
    private Date finishdate;
    private Date quiztime;
    private final Unit unit;


    private final HashMap<String, String> problem_answers = new HashMap<>();

    public Quiz(String startdate, String quiztime, String finishdate, String taskname, Teacher teacher, Unit unit) {
        super(taskname, teacher);
        setStartdate(startdate);
        setQuiztime(quiztime);
        setFinishdate(finishdate);
        this.unit = unit;
    }

    public void setProblem_answers(String problem, String answer) {
        if (problem.isEmpty() || answer.isEmpty()) {
            System.out.println("problem text and answer text can't be empty");
            setProblem_answers(scanner.next(), scanner.next());
        } else {
            problem_answers.put(problem, answer);
        }
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

    private void setFinishdate(String fdate) {
        if (fdate.isEmpty()) {
            System.out.println("finish date can't be empty!try again");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            try {
                this.finishdate = sdf.parse(fdate);
            } catch (ParseException e) {
                System.out.println("finish date and time is incorrect!Day-Month-Year Hours:Minutes");
                setFinishdate(scanner.next());
            }
        }
    }

    public String getFdate() {
        Format formater = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return formater.format(this.finishdate);
    }

}
