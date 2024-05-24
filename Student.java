public class Student extends Person {
    private String major;

    public Student(String firstName, String lastname, String username, String email, String phoneNumber, String role, String pass, String educationalID, String studyField) {
        super(firstName, lastname, username, email, phoneNumber, role, pass, educationalID);
        this.setStudyField(studyField);
    }

    public String getStudyField() {
        return major;
    }

    public void setStudyField(String studyField) {
        if (super.isValidString(studyField)) {
            this.major = studyField;
        } else {
            System.out.println("Study field is not valid");
        }
    }

    @Override
    public void showClasses() {
        if (units.isEmpty()) {
            System.out.println("You dont have class!");
            selectMenu();
        } else {
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i) != null) {
                    System.out.println((i + 1) + units.get(i).unitname);
                }
            }
        }
    }

    @Override
    protected void showTasks() {

    }

    @Override
    public void showNotification() {
        for (Unit unit : units) {
            if (unit.notifications.isEmpty()) {
                System.out.println("You dont have notification!");
                selectMenu();
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

    public void showInfo() {
        super.showInfo();
        System.out.println(getStudyField());
    }
}
