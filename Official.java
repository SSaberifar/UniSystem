public class Official extends Person {
    private String educationalCode;

    public Official(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String educationalCode) {
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
            System.out.println("EducationalCode id not valid");
        }
    }

    @Override
    public void showclasses() {

    }

    @Override
    protected void showtasks() {

    }

    @Override
    public void shownotif() {

    }
}
