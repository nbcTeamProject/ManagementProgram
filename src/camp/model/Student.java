package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private String[] mandatoryArr;
    private String[] choiceArr;


    public Student(String seq, String studentName, String[] mandatoryArr, String[] choiceArr) {
        this.studentId = seq;
        this.studentName = studentName;
        this.mandatoryArr = mandatoryArr;
        this.choiceArr = choiceArr;
    }

    // Getter

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String[] getMandatorySubjects() {
        return mandatoryArr;
    }

    public String[] getChoiceSubjects() {
        return choiceArr;
    }
}
