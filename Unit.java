import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Scanner;

public class Unit {

    Scanner scanner = new Scanner(System.in);
    String unitname ;
    private Student[] students ;
    private Teacher teacher;

    public Unit(String unitname , Teacher teacher) {
        setName(unitname);
        setTeacher(teacher);
    }
    private void setTeacher(Teacher teacher) {
        if ( teacher != null) {
            this.teacher = teacher;
        }
    }
    private void setName(String unitname) {
        if ( !unitname.isEmpty()) {
            this.unitname = unitname;
        } else {
            System.out.println("Unit name cant be empty!");
            setName( scanner.next() );
        }
    }


}
