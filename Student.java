import java.util.regex.Pattern;

public class Student extends Person {

    private String studyfield;
    private String educationalID;

    // Constructor

    // Methods


    // Setter And Getter
    public void setStudyfield( String studyfield ) {
        if ( studyfield.length() <= 18 && super.isvalidstring( studyfield )) {
            this.studyfield = studyfield;
        } else {
            System.out.println("Study field not valid");
            setStudyfield( input.next() );
        }
    }

    public void setEducationalID( String educationalID) {
        if ( super.isvalidpass( educationalID )) {
            this.educationalID = educationalID;
        } else {
            System.out.println(" Educational ID not valid");
            setStudyfield( input.next() );
        }
    }
}
