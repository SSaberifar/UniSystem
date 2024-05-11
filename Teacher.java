public class Teacher extends Person {
    private String educationalCode;

    public Teacher(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String educationalCode) {
        super(fisrtname, lastname, username, email, phonenumber, role, pass);
        this.educationalCode = educationalCode;
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

    @Override
    public boolean equals(Object obj ) {
        return obj instanceof Teacher;
    }

    @Override
    public  String toString() {
        return "TeacherId : " + this.educationalCode;
    }

    public void AddUnit(String unitname) {

        units.add( new Unit(unitname , this));
        System.out.println(" Unit "+ unitname+" Created");
    }

    public void AddStudent(String username) {


    }

}
