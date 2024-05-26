import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Quiz extends Task {

    private Date startdate;
    private Date quiztime;


    private final HashMap<String, String> problem_answers = new HashMap<>();

    public Quiz(String startdate, String quiztime,String deadline, String taskname, Unit unit, Teacher teacher) {
        super(deadline,taskname,unit,teacher);
        setStartdate(startdate);
        setQuiztime(quiztime);
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

}
