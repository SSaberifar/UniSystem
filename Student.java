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
        if (units.isEmpty()){
            System.out.println("You dont have any unit");
            selectMenu();
        } else {
            for ( Unit unit : units){
                System.out.println("unit "+unit.getName()+" tasks :");
                for (Task taskInstance  : unit.getTasks()){
                    if (taskInstance instanceof Quiz quiz){
                        System.out.println("Quiz name : "+quiz.getName()+" deadline : "+quiz.getFdate());
                    }
                }
                System.out.println("---------------------------");
                for ( Task taskInstance : unit.getTasks() ){
                    if ( taskInstance instanceof Question question ){
                        System.out.println("problem text : "+question.getQuestiontext()+ "\t deadline : "+question.getFdate());
                    }
                }
                System.out.println("---------------------------");
            }
            System.out.println("do you want to answer any task?(y/n)");
            if (scanner.next().equals("y")){
                System.out.println("enter unit name : ");
                String inputname = scanner.next();
                for ( Unit unit : units) {
                    if (unit.getName().equals(inputname)){
                        System.out.println("Enter your task name :");
                        String taskname = scanner.next();
                        for (Task task : unit.tasks) {
                            if (task.getName().equals(taskname)) {
                                System.out.println("Do you want to start answering?(y/n)");
                                String accept = scanner.next();
                                if (accept.equals("y") && task instanceof Quiz quiz && unit.getTimeState(quiz.getFdate())){
                                    System.out.println(" quiz name : "+quiz.getName()+" total time : "+ quiz.getQuizTime());
                                    quiz.AnswerToQuiz();
                                    selectMenu();
                                } else if (accept.equals("y") && task instanceof Question question && unit.getTimeState(question.getFdate())) {
                                    System.out.println(" question name : "+question.getName()+" deadline : "+ question.getFdate());
                                    question.AnswerToQuestion();
                                    selectMenu();
                                }else {
                                    selectMenu();
                                }
                            } else {
                                System.out.println("There isn't any task with "+taskname+" name!");
                                selectMenu();
                            }
                        }
                    }
                }
            }
        }

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
