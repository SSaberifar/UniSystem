import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class Task {
    Scanner scanner = new Scanner(System.in);

    private Date deadline;
    private Date remaining_time;
    private String name;
    private boolean done = false;
    private Unit unit;
    private List<Question> questions = new ArrayList<>();

    // Constructor
    public Task(Date finish_date ,String name ,Unit unit) {
        setName(name);
        setFinish_date(finish_date);
        this.unit = unit;
    }

    // Setter And Getter
    private void setName(String name) {
        if (!name.isEmpty()) {
            this.name= name;
        } else {
            System.out.println("Enter valid name for this task");
            setName( scanner.next());
        }
    }

    private void setFinish_date(Date fdate) {
        if (fdate != null) {
            this.deadline = fdate;
        }
    }
}
