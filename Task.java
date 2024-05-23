import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Task {
    Scanner scanner = new Scanner(System.in);

    private Date deadline;
    private Date remaining_time;
    private String name;
    private final Unit unit;


    // Constructor
    public Task(String deadline, String name, Unit unit) {
        setName(name);
        setDeadline(deadline);
        this.unit = unit;
    }

    private void setDeadline(String deadline) {
        if (deadline.isEmpty()) {
            System.out.println("date can't be empty!");
            setDeadline(scanner.next());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            try {
                this.deadline = sdf.parse(deadline);
            } catch (ParseException e) {
                System.out.println(" deadline time is invalid! enter hours:minutes");
                setDeadline(scanner.next());
            }
        }
    }

    public String getName() {
        return this.name;
    }

    // Setter And Getter
    private void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Enter valid name for this task");
            setName(scanner.next());
        }
    }

    public Date getDeadline(){
        return this.deadline;
    }
}
