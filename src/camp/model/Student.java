
package camp.model;
import camp.model.Score;
import java.util.ArrayList;

public class Student {
    private  String studentId;
    private  String studentName;
    private  ArrayList<Subject> mandatoryArr;
    private  ArrayList<Subject> choiceArr;


    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }


    // Getter

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public  ArrayList<Subject> getMandatorySubjects() {
        return mandatoryArr;
    }

    public  ArrayList<Subject> getChoiceSubjects() {
        return choiceArr;
    }

    //Setter

    //필수과목 저장
    public void setMandatoryArr(ArrayList<Subject> inputMandatoryArr){
        for(Subject sub : inputMandatoryArr){
            this.mandatoryArr.add(sub);
        }
    }

    //선택과목 저장
    public void setChoiceArr(ArrayList<Subject> inputChoiceArr){
        for(Subject sub : inputChoiceArr){
            this.choiceArr.add(sub);
        }
    }
}