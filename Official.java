public class Official extends Person {
    private String educationalCode;

    public Official(String fisrtname, String lastname, String username, String email, String phonenumber, String role, String pass, String educationalCode) {
        super(fisrtname, lastname, username, email, phonenumber, role, pass);
        try {
            this.setEducationalCode(educationalCode);
        } catch (Exceptions.InvalidIDException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEducationalCode() {
        return educationalCode;
    }

    public void setEducationalCode(String educationalCode) throws Exceptions.InvalidIDException {
        if (super.isValidId(educationalCode, getRole())) {
            this.educationalCode = educationalCode;
            System.out.println("ID Processed");
        } else {
            throw new Exceptions.InvalidIDException("please enter valid educational code");
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
