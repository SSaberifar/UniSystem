import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Task {
    protected static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    protected Date deadline;
    protected String taskName;
    protected Teacher taskTeacher;
    protected Unit taskUnit;

    // Constructor
    public Task(String deadline, String taskName, Teacher taskTeacher, Unit taskUnit) {
        setName(taskName);
        setDeadline(deadline);
        setTaskTeacher(taskTeacher);
        setTaskUnit(taskUnit);
    }


    // Getter and Setter for Task Unit
    public Unit getTaskUnit() {
        return taskUnit;
    }

    public void setTaskUnit(Unit taskUnit) {
        this.taskUnit = taskUnit;
    }

    // Getter and Setter for Task Teacher
    public Teacher getTaskTeacher() {
        return taskTeacher;
    }

    public void setTaskTeacher(Teacher taskTeacher) {
        this.taskTeacher = taskTeacher;
    }

    // Setter for Deadline with Validation
    public void setDeadline(String deadline) {
        while (true) {
            if (deadline.isEmpty()) {
                System.out.println("Date can't be empty! Enter the deadline:");
                deadline = scanner.next();
                continue;
            }
            try {
                this.deadline = DATE_FORMAT.parse(deadline);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            break;
        }
    }

    // Getter for Deadline Date
    public Date getDeadlineDate() {
        return this.deadline;
    }

    // Getter and Setter for Task Name with Validation
    public String getName() {
        return this.taskName;
    }

    public void setName(String name) {
        while (name.isEmpty()) {
            System.out.println("Enter a valid name for this task:");
            name = scanner.next();
        }
        this.taskName = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "deadline=" + deadline +
                ", taskName='" + taskName + '\'' +
                ", taskTeacher=" + taskTeacher +
                ", taskUnit=" + taskUnit +
                '}';
    }
}
