import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Unit {

    Scanner scanner = new Scanner(System.in);
    String unitname;
    List<Student> students = new ArrayList<>(10);
    List<String> notifications = new ArrayList<>(10);
    private Teacher teacher;

    public Unit(String unitname, Teacher teacher) {
        setUnitname(unitname);
        setTeacher(teacher);
    }

    private void setTeacher(Teacher teacher) {
        if (teacher != null) {
            this.teacher = teacher;
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
}
