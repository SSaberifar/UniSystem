import java.util.Date;
import java.util.List;

public class Lesson extends Task {
    private String name;
    private Teacher teacher;
    private List<Student> students;


    public Lesson(Date finish_date, String name) {
        super(finish_date, name);
    }
}
