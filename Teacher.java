import java.util.List;

public class Teacher extends Person {
    private String educationalCode;

    public Teacher(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String educationalCode) {
        super(fisrtname, lastname, username, email, phonenumber, role, pass);
        this.setEducationalCode(educationalCode);
    }

    public String getEducationalCode() {
        return educationalCode;
    }

    public void setEducationalCode(String educationalCode) {
        if (super.isValidId(educationalCode, getRole())) {
            this.educationalCode = educationalCode;
        } else {
            System.out.println(" EducationalCode id not valid");
        }
    }

    public void AddUnit(String unitname) {
        units.add( new Unit(unitname , this));
        System.out.println("Unit "+unitname+" Created!");
    }

    public void AddStudent(Unit unit,String username) {
        for(Student student:Main.students){
            if(student.getUsername().equals(username)){
                unit.setStudents(student);
                System.out.println("Student "+username+" Added!");
                break;
            }
        }
    }

    @Override
    protected void showtasks() {

    }
}
