import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Unit {

    Scanner scanner = new Scanner(System.in);
    String unitname;
    List<Student> students = new ArrayList<>(10);
    List<String> notifications = new ArrayList<>(10);
    List<Question> questions = new ArrayList<>();
    List<Quiz> quizzes = new ArrayList<>();
    private int currentquize = 0;
    private Teacher teacher;

    public Unit(String unitname, Teacher teacher) {
        setUnitname(unitname);
        setTeacher(teacher);
    }

    // Methods
    public void AddQuestion(String deadline, String name, String questiontext, String answertext, Unit unit) {

        if (!deadline.isEmpty() && !name.isEmpty() && !questiontext.isEmpty() && !answertext.isEmpty()) {
            questions.add(new Question(deadline, name, questiontext, answertext, this));
            System.out.println("question added , do you want to add another question?y/n");
            if (scanner.next().charAt(0) == 'y') {
                System.out.println("enter date, then question name ,text and answer");
                AddQuestion(scanner.next(), scanner.next(), scanner.next(), scanner.next(), this);
            }
        } else {
            System.out.println("deadline and question name can't be empty , do you want try again?y/n");
            if (scanner.next().charAt(0) == 'y') {
                System.out.println("enter date, then question name ,text and answer");
                AddQuestion(scanner.next(), scanner.next(), scanner.next(), scanner.next(), this);
            }
        }
    }

    public void AddQuiz(String startdate, String quiztime, String finishdate, String quizname) {
        if (startdate.isEmpty() || quiztime.isEmpty() || finishdate.isEmpty() || quizname.isEmpty()) {
            System.out.println("start/finish date and quiz date/name can't be empty! try again");
            AddQuiz(scanner.next(), scanner.next(), scanner.next(), scanner.next());
        } else {
            quizzes.add(new Quiz(startdate, quiztime, finishdate, quizname, this.teacher, this));
            currentquize++;
            boolean repeat = false;
            do {
                repeat = false;
                System.out.println("enter your questions and answers :");
                quizzes.get(currentquize).setProblem_answers(scanner.next(), scanner.next());
                System.out.println("question and answer add successfully do you want to add another problem?y/n");
                if (scanner.next().charAt(0) == 'y') {
                    repeat = true;
                }
            } while (repeat);

        }
    }

    private void getQuestionState(String deadline) {
        LocalDateTime finishtime= LocalDateTime.parse(deadline);
        Duration duration = Duration.between(LocalDateTime.now(),finishtime);
        if ( finishtime.isAfter(LocalDateTime.now())){
            System.out.println("You have "+duration.toHours()+" hours and "+duration.toMinutes()+" minutes time for this question");
        } else {
            System.out.println(duration.toHours()+"hours and "+duration.toMinutes()+" minutes have pass since the homework deadline!");
        }
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        if (!unitname.isEmpty()) {
            this.unitname = unitname;
        } else {
            System.out.println("Unit name cant be empty!");
            setUnitname(scanner.next());
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(Student student) {
        students.add(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    private void setTeacher(Teacher teacher) {
        if (teacher != null) {
            this.teacher = teacher;
        }
    }
}
