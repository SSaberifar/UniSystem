import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Quiz extends Task {
    private Date startdate;
    private Date quiztime;
    private int score;

    private final HashMap<String, String> problemAnswers = new HashMap<>();
    private final HashMap<Student, String> studentAnswers = new HashMap<>();
    private final List<HashMap<Student, String>> studentsHolder = new ArrayList<>();
    private final HashMap<String, Integer> studentsScore = new HashMap<>();

    public Quiz(String startdate, String quiztime, String deadline, String taskname, Unit unit, Teacher teacher, int score) {
        super(deadline, taskname, teacher, unit);
        setStartdate(startdate);
        setQuiztime(quiztime);
        setScore(score);
    }

    public HashMap<String, String> getProblemAnswers() {
        return problemAnswers;
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
            problemAnswers.put(problem, answer);
        }
    }

    public void answerToQuiz(Student student) {
        int index = 0;
        System.out.println("Enter answers:");
        int startMinute = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        while (index < problemAnswers.size()) {
            System.out.println((index + 1) + ") " + problemAnswers.keySet().toArray()[index] + ": \t\t score: " + score);
            String answer = scanner.nextLine();

            int answerMinute = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
            int totalQuizTime = Integer.parseInt(getQuizTime().split(":")[0]) * 60 + Integer.parseInt(getQuizTime().split(":")[1]);

            if ((answerMinute - startMinute) < totalQuizTime) {
                studentAnswers.put(student, answer);
                index++;
            } else {
                System.out.println("The exam time is over.");
                break;
            }
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
                this.startdate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(date);
            } catch (ParseException e) {
                System.out.println("Date and time format incorrect! Use format: dd-MM-yyyy HH:mm");
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
                super.setDeadline(String.valueOf(new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(deadline)));
            } catch (ParseException e) {
                System.out.println("Deadline format incorrect! Use format: dd-MM-yyyy HH:mm");
                setDeadline(scanner.next());
            }
        }
    }

    public String getFormattedDate(Date date, String pattern) {
        Format formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public String getFdate() {
        return getFormattedDate(super.getDeadlineDate(), "dd-MM-yyyy HH:mm");
    }

    public String getSdate() {
        return getFormattedDate(startdate, "dd-MM-yyyy HH:mm");
    }

    public String getQuizTime() {
        return getFormattedDate(quiztime, "HH:mm");
    }

    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        } else {
            System.out.println("Invalid score number! Enter a valid score:");
            setScore(scanner.nextInt());
        }
    }
}
