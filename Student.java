public class Student extends Person {

    private String major;
    private String educationalID;

    public Student(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String studyfield, String educationalID) {
        super(fisrtname, lastname, username, email, phonenumber, role, pass);
        this.major = studyfield;
        this.educationalID = educationalID;
    }

    public String getStudyfield() {
        return major;
    }

    public String getEducationalID() {
        return educationalID;
    }

    public void setStudyfield(String studyfield) {
        if (super.isValidString(studyfield)) {
            this.major = studyfield;
        } else {
            System.out.println("Study field is not valid");
        }
    }

    public void setEducationalID(String educationalID) {
        if (super.isValidId(educationalID, getRole())) {
            this.educationalID = educationalID;
        } else {
            System.out.println(" EducationalID is not valid");
        }
    }

}
