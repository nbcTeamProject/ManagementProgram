
package camp.model;

import java.util.ArrayList;

public class Student {
    private String studentId;
    private String studentName;
    private  ArrayList<String> mandatoryArr;
    private  ArrayList<String> choiceArr;


    public Student(String seq, String studentName, ArrayList<String> mandatoryArr, ArrayList<String> choiceArr) {
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

    public  ArrayList<String> getMandatorySubjects() {
        return mandatoryArr;
    }

    public  ArrayList<String> getChoiceSubjects() {
        return choiceArr;
    }
}