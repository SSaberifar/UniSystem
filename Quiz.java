import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Quiz extends Task {
    private Date startdate;
    private Date quiztime;
    private int score;
    private String quizTxt;
    private String quizAns;

    private final List<String> answers = new ArrayList<>(4);
    private final HashMap<Student, String> studentAnswers = new HashMap<>();
    private final List<HashMap<Student, String>> studentsHolder = new ArrayList<>();
    private final HashMap<String, Integer> studentsScore = new HashMap<>();

    public Quiz(String startdate, String quiztime, String deadline, String taskname, Unit unit, Teacher teacher, int score) {
        super(deadline, taskname, teacher, unit);
        setStartdate(startdate);
        setQuiztime(quiztime);
        setScore(score);
    }

    public HashMap<Student, String> getStudentAnswers() {
        return studentAnswers;
    }

    public List<HashMap<Student, String>> getStudentsHolder() {
        return studentsHolder;
    }

    public HashMap<String, Integer> getStudentsScore() {
        return studentsScore;
    }

    public void setProblemAnswers(String problem, String answer) {
        if (problem.isEmpty() || answer.isEmpty()) {
            System.out.println("Problem text and answer text can't be empty. Enter problem and answer again:");
            setProblemAnswers(scanner.next(), scanner.next());
        } else {
            this.quizTxt = problem;
            for(int i=0; i<4 ;i++){
                String test = scanner.next();
                answers.add(test);
            }
            this.quizAns = answer;
        }
    }

    public void answerToQuiz(Student student) {
        System.out.println("Enter answer:");
        int startMinute = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        String answer = scanner.nextLine();
        int answerMinute = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        int totalQuizTime = Integer.parseInt(getQuizTime().split(":")[0]) * 60 + Integer.parseInt(getQuizTime().split(":")[1]);
        if ((answerMinute - startMinute) < totalQuizTime) {
            studentAnswers.put(student, answer);
        } else {
            System.out.println("The exam time is over.");
        }
        studentsHolder.add(new HashMap<>(studentAnswers));
        System.out.println("Your answers were saved successfully.");
    }

    public void setStartdate(String date) {
        if (date.isEmpty()) {
            System.out.println("Quiz start date can't be empty. Enter the start date:");
            setStartdate(scanner.next());
        } else {
            try {
                this.startdate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
            } catch (ParseException e) {
                System.out.println("Date and time format incorrect! Use format: yyyy-MM-dd HH:mm");
                setStartdate(scanner.next());
            }
        }
    }

    public void setQuiztime(String time) {
        if (time.isEmpty()) {
            System.out.println("Quiz time can't be empty. Enter quiz time:");
            setQuiztime(scanner.next());
        } else {
            try {
                this.quiztime = new SimpleDateFormat("HH:mm").parse(time);
            } catch (ParseException e) {
                System.out.println("Quiz time format incorrect! Use format: HH:mm");
                setQuiztime(scanner.next());
            }
        }
    }

    @Override
    public void setDeadline(String deadline) {
        if (deadline.isEmpty()) {
            System.out.println("Deadline can't be empty. Enter the deadline:");
            setDeadline(scanner.next());
        } else {
            try {
                super.setDeadline(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(deadline)));
            } catch (ParseException e) {
                System.out.println("Deadline format incorrect! Use format: yyyy-MM-dd HH:mm");
                setDeadline(scanner.next());
            }
        }
    }

    public String getFormattedDate(Date date, String pattern) {
        Format formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public String getFdate() {
        return getFormattedDate(super.getDeadlineDate(), "yyyy-MM-dd HH:mm");
    }

    public String getSdate() {
        return getFormattedDate(startdate, "yyyy-MM-dd HH:mm");
    }

    public String getQuizTime() {
        return getFormattedDate(quiztime, "HH:mm");
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public String getQuizTxt() {
        return quizTxt;
    }

    public String getQuizAns() {
        return quizAns;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "startdate=" + startdate +
                ", quiztime=" + quiztime +
                ", score=" + score +
                ", quizTxt='" + quizTxt + '\'' +
                ", quizAns='" + quizAns + '\'' +
                ", studentAnswers=" + studentAnswers +
                ", studentsHolder=" + studentsHolder +
                ", studentsScore=" + studentsScore +
                '}';
    }
}
