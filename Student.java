public class Student extends Person {
    private String major;
    private String educationalID;

    public Student(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String studyfield, String educationalID) {
        super(fisrtname, lastname, username, email, phonenumber, role, pass);
        this.setStudyfield(studyfield);
        try {
            this.setEducationalID(educationalID);
        } catch (Exceptions.InvalidIDException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStudyfield() {
        return major;
    }

    public void setStudyfield(String studyfield) {
        if (super.isValidString(studyfield)) {
            this.major = studyfield;
        } else {
            System.out.println("Study field is not valid");
        }
    }

    public String getEducationalID() {
        return educationalID;
    }

    public void setEducationalID(String educationalID) throws Exceptions.InvalidIDException {
        if (super.isValidId(educationalID, getRole())) {
            this.educationalID = educationalID;
            System.out.println("ID Processed");
        } else {
            throw new Exceptions.InvalidIDException("please enter valid student id");
        }
    }

    @Override
    public void showclasses() {
        if (units.isEmpty()) {
            System.out.println("You dont have class!");
            selectmenu();
        } else {
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i) != null) {
                    System.out.println((i + 1) + units.get(i).unitname);
                }
            }
        }
    }

    @Override
    protected void showtasks() {

    }

    @Override
    public void shownotif() {
        for (Unit unit : units) {
            if (unit.notifications.isEmpty()) {
                System.out.println("You dont have notification!");
                selectmenu();
            } else {
                for (int i = 0; i < unit.notifications.size(); i++) {
                    if (unit.notifications.get(i) != null) {
                        System.out.println(unit.getUnitname() + ":");
                        System.out.println((i + 1) + unit.notifications.get(i));
                    }
                }
            }
        }
    }
}
