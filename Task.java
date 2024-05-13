import java.util.Date;
import java.util.Scanner;

public abstract class Task {
    Scanner scanner = new Scanner(System.in);

    private Date finish_date;
    private Date remaining_time;
    private String name;
    private boolean done = false;

    // Constructor
    public Task(Date finish_date ,String name ) {
        setName(name);
        setFinish_date(finish_date);
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
            this.finish_date = fdate;
        }
    }
}
