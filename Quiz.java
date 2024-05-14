import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Quiz extends Unit{

    private String name;
    private Date date;
    private Unit unit;
    private List<Question> questions;
    private int score;

    public Quiz(Date finish_date, String name ,String unitname, Teacher teacher) {
        super(unitname,teacher);

    }
}
